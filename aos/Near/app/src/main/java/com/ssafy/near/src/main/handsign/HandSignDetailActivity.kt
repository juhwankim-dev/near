package com.ssafy.near.src.main.handsign

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityHandSignDetailBinding
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.repository.HandSignRepository

class HandSignDetailActivity : BaseActivity<ActivityHandSignDetailBinding>(R.layout.activity_hand_sign_detail)  {
    private lateinit var handSignViewModel: HandSignViewModel
    private var videoPlayer: SimpleExoPlayer? = null
    private var sampleUrl = ""
    private var handSignInfo: HandSignInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        handSignViewModel = ViewModelProvider(this, HandSignViewModelFactory(HandSignRepository()))
            .get(HandSignViewModel::class.java)

        handSignViewModel.getAddBookmark().observe(this) {
            showToastMessage("내 단어장에 추가하였습니다")
        }
    }

    private fun initView() {
        if(intent.hasExtra("handSignInfo")) {
            (intent.getSerializableExtra("handSignInfo") as HandSignInfo).apply {
                binding.handSignInfo = this
                handSignInfo = this
                sampleUrl = this.video_path
            }
        } else {
            showToastMessage("데이터를 불러오지 못했습니다")
        }

        // ExoPlayer 인스턴스를 생성하고 소스를 플레이에 할당하여 비디오 플레이어 초기화
        videoPlayer = SimpleExoPlayer.Builder(this).build()
        binding.pvHandSign.player = videoPlayer
        buildMediaSource()?.let {
            videoPlayer?.prepare(it)
        }
    }

    private fun initEvent() {
        binding.ivBookmark.setOnClickListener {
            if(handSignInfo == null) {
                showToastMessage("데이터를 불러오지 못했습니다")
            } else {
                handSignViewModel.addBookmark(handSignInfo!!.handcontent_key.toString(), sSharedPreferences.getUserId().toString())
            }
        }
    }

    // MediaSource: 영상에 출력할 미디어 정보를 가져오는 클래스
    private fun buildMediaSource(): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(sampleUrl))
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
        if (isFinishing) {
            releasePlayer()
        }
    }

    // 종료
    private fun releasePlayer() {
        videoPlayer?.release()
    }
}