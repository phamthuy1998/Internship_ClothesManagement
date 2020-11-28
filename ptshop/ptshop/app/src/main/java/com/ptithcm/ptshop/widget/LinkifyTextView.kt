package com.ptithcm.ptshop.widget


//open class LinkifyTextView : AppCompatTextView {

   /* private var onClickLinkify: (String, Int) -> Unit = { _, _ -> }

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    val linkTextPaint: TextPaint? = TextPaint().apply {
        color = ContextCompat.getColor(context, R.color.blue)
    }
    val hashTagTextPaint: TextPaint? = TextPaint().apply {
        color = ContextCompat.getColor(context, R.color.blue)
    }

    val mentionTextPaint: TextPaint? = TextPaint().apply {
        color = ContextCompat.getColor(context, R.color.mention_color)
    }

    private fun init(context: Context?) {
        context ?: return
        highlightColor = ContextCompat.getColor(this.context, R.color.blue_cyan)
        isClickable = false
        addTextChange()
        createMovementMethod()
    }

    fun createMovementMethod(): MyLinkMovementMethod {
        return MyLinkMovementMethod.linkify(
            this,
            arrayListOf(
                Pair(CustomLinkify.WEB_URLS, linkTextPaint),
                Pair(CustomLinkify.MENTION, mentionTextPaint),
                Pair(CustomLinkify.HASH_TAG, hashTagTextPaint)
            ),
            false
        ) { string, mode ->
            if (mode == CustomLinkify.WEB_URLS)
                null
//                openWebView(context, string)
            else
                onClickLinkify.invoke(string, mode)
        }
    }

    private fun addTextChange() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                CustomLinkify.addLinks(
                    this@LinkifyTextView,
                    arrayListOf(
                        Pair(CustomLinkify.WEB_URLS, linkTextPaint),
                        Pair(CustomLinkify.MENTION, mentionTextPaint),
                        Pair(CustomLinkify.HASH_TAG, hashTagTextPaint)
                    ),
                    false
                ) { string, mode ->
                    if (mode == CustomLinkify.WEB_URLS)
//                        openWebView(context, string)
                    else
                        onClickLinkify.invoke(string, mode)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun setOnClickLinkify(onClickLinkify: (String, Int) -> Unit) {
        this.onClickLinkify = onClickLinkify
        createMovementMethod()
    }

    fun setHighLightLinkify(stringHighLight: String?) {
        stringHighLight ?: return
        try {
            addTextChangedListener {
                val pattern = Pattern.compile("\\B$stringHighLight\\b", Pattern.MULTILINE)
                val matcher = pattern.matcher(this.text.toString())
                while (matcher.find()) {
                    val start = matcher.start()
                    val end = matcher.end()
                    val span = object : ClickableSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            ds.apply {
                                bgColor = ContextCompat.getColor(
                                    this@LinkifyTextView.context,
                                    R.color.high_light_text
                                )
                            }
                        }

                        override fun onClick(widget: View) = Unit
                    }
                    if (start < 0 || end < 0 || end <= start) return@addTextChangedListener
                    (this.text as Spannable).run {
                        if (end <= this.length && start >= 0) {
                            this.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    }
                }
            }
        } catch (e: Exception) {
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = ceil(getMaxLineWidth(layout).toDouble()).toInt()
        val height = measuredHeight
        setMeasuredDimension(width, height)
    }

    private fun getMaxLineWidth(layout: Layout): Float {
        var maximumWidth = 0.0f
        val lines = layout.lineCount
        for (i in 0 until lines) {
            maximumWidth = max(layout.getLineWidth(i), maximumWidth)
        }
        return maximumWidth
    }*/
//}