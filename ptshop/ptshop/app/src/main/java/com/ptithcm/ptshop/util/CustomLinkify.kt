package com.ptithcm.ptshop.util

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.EventLog
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

object CustomLinkify {

    private const val LOG_TAG = "Linkify"

    const val LINKIFY_NONE = -2
    const val WEB_URLS = 1
    const val MENTION = 2
    const val HASH_TAG = 3
    const val ALL = WEB_URLS or MENTION or HASH_TAG

    const val MIN_CLICK_INTERVAL: Long = 600
    var isViewClicked = false

    /**
     * Don't treat anything with fewer than this many digits as a
     * phone number.
     */
    private const val PHONE_NUMBER_MINIMUM_DIGITS = 5

    private fun containsUnsupportedCharacters(text: String) =
        when {
            text.contains("\u202C") -> {
                Log.e(LOG_TAG, "Unsupported character for applying links: u202C")
                true
            }
            text.contains("\u202D") -> {
                Log.e(LOG_TAG, "Unsupported character for applying links: u202D")
                true
            }
            text.contains("\u202E") -> {
                Log.e(LOG_TAG, "Unsupported character for applying links: u202E")
                true
            }
            else -> false
        }

    fun addLinks(
        text: TextView,
        masks: ArrayList<Pair<Int, TextPaint?>>,
        isRemoveOldSpannable: Boolean = true,
        setOnClickListener: (String, Int) -> Unit = { _, _ -> }
    ): Boolean {
        for (mask in masks) {
            if (mask.first == LINKIFY_NONE) {
                return false
            }
        }

        val t = text.text
        return if (t is Spannable) {
            if (addLinks(t, masks, null, isRemoveOldSpannable, setOnClickListener)) {
                addLinkMovementMethod(
                    text
                )
                return true
            }
            false
        } else {
            val s = SpannableString.valueOf(t)
            if (addLinks(s, masks, null, isRemoveOldSpannable, setOnClickListener)) {
                addLinkMovementMethod(
                    text
                )
                text.text = s
                return true
            }
            false
        }
    }

    private fun addLinkMovementMethod(t: TextView) {
        val m = t.movementMethod
        if (m == null || m !is LinkMovementMethod) {
            if (t.linksClickable) {
                t.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    private fun addLinks(
        text: Spannable?, masks: ArrayList<Pair<Int, TextPaint?>>,
        urlSpanFactory: ((String) -> URLSpan)? = null,
        isRemoveOldSpannable: Boolean = true,
        setOnClickListener: (String, Int) -> Unit = { _, _ -> }
    ): Boolean {
        if (text != null && containsUnsupportedCharacters(text.toString())) {
            EventLog.writeEvent(0x534e4554, "116321860", -1, "")
            return false
        }

        text ?: return false

        if (isRemoveOldSpannable) {
            val toRemoveSpans = text.getSpans(0, text.length, ClickableSpan::class.java)
            if (toRemoveSpans.isNotEmpty()) {
                toRemoveSpans.forEach {
                    text.removeSpan(it)
                }
            }
        }

        val links = ArrayList<LinkSpec>()

        for (mask in masks) {
            if (mask.first == WEB_URLS) {
                gatherLinks(
                    mask,
                    links,
                    text,
                    MyPatterns.AUTOLINK_WEB_URL,
                    arrayOf("http://", "https://", "rtsp://"),
                    sUrlMatchFilter,
                    null
                )
            }

            if (mask.first == MENTION) {
                gatherLinks(
                    mask,
                    links,
                    text,
                    MyPatterns.AUTOLINK_MENTION,
                    arrayOf(),
                    null,
                    null
                )
            }

            if (mask.first == HASH_TAG) {
                gatherLinks(
                    mask,
                    links,
                    text,
                    MyPatterns.AUTOLINK_HASH_TAG,
                    arrayOf(),
                    null,
                    null
                )
            }
        }

        pruneOverlaps(links)
        if (links.size == 0) {
            return false
        }
        for (link in links) {
            applyLink(link, text, urlSpanFactory, setOnClickListener)
        }

        return true
    }

    private fun applyLink(
        linkSpec: LinkSpec, text: Spannable,
        urlSpanFactory: ((String) -> URLSpan)?,
        setOnClickListener: (String, Int) -> Unit = { _, _ -> }
    ) {
        var mLastClickTime: Long = 0

        val span = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.apply {
                    linkSpec.mask.second ?: return@apply
                    isUnderlineText = linkSpec.mask.second!!.isUnderlineText
                    // todo
//                    typeface = linkSpec.mask.second!!.typeface
                    color = linkSpec.mask.second!!.color
                }
            }

            override fun onClick(widget: View) {
                Log.d("Linkify", "Value: ${linkSpec.url} - Type: ${linkSpec.mask.first}")

                val currentClickTime: Long = SystemClock.uptimeMillis()
                val elapsedTime = currentClickTime - mLastClickTime
                mLastClickTime = currentClickTime
                if (elapsedTime <= MIN_CLICK_INTERVAL) return
                if (!isViewClicked) {
                    isViewClicked = true
                    startTimer()
                } else {
                    return
                }

                setOnClickListener.invoke(linkSpec.url, linkSpec.mask.first)
            }
        }
        if (linkSpec.end <= text.length && linkSpec.start >= 0) {
            text.setSpan(span, linkSpec.start, linkSpec.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun startTimer() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ isViewClicked = false }, MIN_CLICK_INTERVAL)
    }

    private fun makeUrl(
        url: String, prefixes: Array<String?>,
        matcher: Matcher, filter: Linkify.TransformFilter?
    ): String {
        var newUrl = url
        if (filter != null) {
            newUrl = filter.transformUrl(matcher, newUrl)
        }
        var hasPrefix = false
        for (i in prefixes.indices) {
            if (newUrl.regionMatches(
                    0,
                    prefixes[i]!!,
                    0,
                    prefixes[i]!!.length,
                    ignoreCase = true
                )
            ) {
                hasPrefix = true
                // Fix capitalization if necessary
                if (!newUrl.regionMatches(
                        0,
                        prefixes[i]!!,
                        0,
                        prefixes[i]!!.length,
                        ignoreCase = false
                    )
                ) {
                    newUrl = prefixes[i] + newUrl.substring(prefixes[i]!!.length)
                }
                break
            }
        }
        if (!hasPrefix && prefixes.isNotEmpty()) {
            newUrl = prefixes[0] + newUrl
        }
        return newUrl
    }

    private fun gatherLinks(
        mask: Pair<Int, TextPaint?>,
        links: ArrayList<LinkSpec>,
        s: Spannable,
        pattern: Pattern,
        schemes: Array<String?>,
        matchFilter: Linkify.MatchFilter?,
        transformFilter: Linkify.TransformFilter?
    ) {
        val m = pattern.matcher(s)
        while (m.find()) {

            val start = m.start()
            val end = m.end()

            if (mask.first == WEB_URLS && start > 0 && s.toString()[start - 1] == '#')
                continue

            if (matchFilter == null || matchFilter.acceptMatch(s, start, end)) {
                val spec = LinkSpec()
                val url = makeUrl(
                    m.group(0), schemes, m, transformFilter
                )

                spec.url = url
                spec.start = start
                spec.end = end
                spec.mask = mask
                links.add(spec)
            }
        }
    }

    private fun pruneOverlaps(links: ArrayList<LinkSpec>) {
        val c = Comparator<LinkSpec> { a, b ->
            if (a.start < b.start) {
                return@Comparator -1
            }
            if (a.start > b.start) {
                return@Comparator 1
            }
            if (a.end < b.end) {
                return@Comparator 1
            }
            if (a.end > b.end) {
                -1
            } else 0
        }
        Collections.sort(links, c)
        var len = links.size
        var i = 0
        while (i < len - 1) {
            val a = links[i]
            val b = links[i + 1]
            var remove = -1
            if (a.start <= b.start && a.end > b.start) {
                if (b.end <= a.end) {
                    remove = i + 1
                } else if (a.end - a.start > b.end - b.start) {
                    remove = i + 1
                } else if (a.end - a.start < b.end - b.start) {
                    remove = i
                }
                if (remove != -1) {
                    links.removeAt(remove)
                    len--
                    continue
                }
            }
            i++
        }
    }

    /**
     * Default factory function to create [URLSpan]s. While adding spans to a
     * [Spannable], [Linkify] will call this function to create a [URLSpan].
     */
    private val DEFAULT_SPAN_FACTORY: (String) -> URLSpan = {
        URLSpan(it)
    }

    private val sUrlMatchFilter =
        Linkify.MatchFilter { s, start, end ->
            if (start == 0) {
                return@MatchFilter true
            }
            s[start - 1] != '@'
        }
}

internal class LinkSpec {
    var url: String = ""
    var start = 0
    var end = 0
    var mask: Pair<Int, TextPaint?> = Pair(-2, TextPaint())
}