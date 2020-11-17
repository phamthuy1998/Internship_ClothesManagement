package com.ptithcm.admin.view.wishlist.overview

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ExoPlayerFactory.newSimpleInstance
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ptithcm.admin.R
import com.ptithcm.admin.constant.KEY_ARGUMENT_OBJECT
import kotlinx.android.synthetic.main.fragment_video_overview.*

class VideoFragment(val listener: ((Any) -> Unit)? = null ): Fragment() {

    companion object {
        fun newInstance(promotionBanner: String?, listener: ((Any) -> Unit)? = null): VideoFragment {
            val fragment = VideoFragment(listener)
            fragment.arguments = Bundle().apply {
                putString(KEY_ARGUMENT_OBJECT, promotionBanner)
            }
            return fragment
        }
    }

    private var url: String = ""
    private var player: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_video_overview, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(KEY_ARGUMENT_OBJECT) ?: ""
        }
        val mediaSource = newVideoSource(url)
        player = newSimpleExoPlayer()
        player?.prepare(mediaSource)
        player?.addListener(object : Player.EventListener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when(playbackState){
                    Player.STATE_READY -> {
                        controller?.findViewById<AppCompatTextView>(R.id.exoTotalDuration_custom)?.text = getString(R.string.time_second,(player?.duration?.div(1000))?.toInt().toString())
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.stop()
        player = null
    }

    private fun initVideo(){
        sepVideo?.apply {
            player = this@VideoFragment.player
            player?.playWhenReady = true
            useController = false
        }
        controller.player = player
    }

    private fun newSimpleExoPlayer(): SimpleExoPlayer {
        val bandwidthMeter = DefaultBandwidthMeter.Builder(context)
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val loadControl = DefaultLoadControl()
        return newSimpleInstance(
            activity,
            DefaultRenderersFactory(context), trackSelector,
            loadControl, null, bandwidthMeter.build()
        )
    }

    private fun newVideoSource(url: String): ProgressiveMediaSource {
        val bandwidthMeter = DefaultBandwidthMeter.Builder(context).build()
        val userAgent = Util.getUserAgent(activity, getString(R.string.app_name))
        val dataSourceFactory = DefaultDataSourceFactory(activity, userAgent, bandwidthMeter)
        val extractorsFactory = DefaultExtractorsFactory()
        return ProgressiveMediaSource.Factory(dataSourceFactory, extractorsFactory).createMediaSource(
            Uri.parse(url))
    }
}