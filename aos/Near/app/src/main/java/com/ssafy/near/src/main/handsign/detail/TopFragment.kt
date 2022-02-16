package com.ssafy.near.src.main.handsign.detail

import android.os.Bundle
import android.view.View
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentTopBinding
import com.ssafy.near.src.main.handsign.HandSignFragment
import com.ssafy.near.src.main.handsign.HandSignViewModel

class TopFragment : BaseFragment<FragmentTopBinding>(R.layout.fragment_top), Player.EventListener {
    private lateinit var handSignViewModel: HandSignViewModel
    private var videoPlayer: SimpleExoPlayer? = null
    private var videoUrl = "https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0001_SYN01_F.mp4"

    val ivPlay: ImageView by lazy {
        requireActivity().findViewById(R.id.iv_play)
    }

    val ivPause: ImageView by lazy {
        requireActivity().findViewById(R.id.iv_pause)
    }

    val ivClose: ImageView by lazy {
        requireActivity().findViewById(R.id.iv_close)
    }

    val tvTitle: TextView by lazy {
        requireActivity().findViewById(R.id.tv_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        handSignViewModel = (parentFragment as HandSignFragment).handSignViewModel
        videoUrl = handSignViewModel.selectedHandSignInfo!!.video_path
    }

    fun initView() {
        // ExoPlayer 인스턴스를 생성하고 소스를 플레이에 할당하여 비디오 플레이어 초기화
        videoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        binding.pvHandSign.player = videoPlayer
        buildMediaSource()?.let {
            videoPlayer?.prepare(it)
        }

        tvTitle.text = (parentFragment as HandSignFragment).handSignViewModel.selectedHandSignInfo!!.name
    }

    fun initEvent() {
        ivPlay.setOnClickListener {
            videoPlayer?.playWhenReady = true
            ivPlay.visibility = View.GONE
            ivPause.visibility = View.VISIBLE
        }

        ivPause.setOnClickListener {
            videoPlayer?.playWhenReady = false
            ivPlay.visibility = View.VISIBLE
            ivPause.visibility = View.GONE
        }

        ivClose.setOnClickListener {
            videoPlayer?.release()
            (parentFragment as HandSignFragment).removeFragment()
        }
    }

    // MediaSource: 영상에 출력할 미디어 정보를 가져오는 클래스
    private fun buildMediaSource(): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl))
    }

    // 일시중지
    override fun onResume() {
        super.onResume()
        videoPlayer?.playWhenReady = true
    }

    // 정지
    override fun onStop() {
        super.onStop()
        videoPlayer?.playWhenReady = false
        if (requireActivity().isFinishing) {
            releasePlayer()
        }
    }

    // 종료
    private fun releasePlayer() {
        videoPlayer?.release()
    }
}