package com.ssafy.near.src.main.handsign

import android.content.Intent
import android.os.Bundle
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityHandSignDetailBinding

class HandSignDetailActivity : BaseActivity<ActivityHandSignDetailBinding>(R.layout.activity_hand_sign_detail)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        if(intent.hasExtra("id")) {
            // TODO id에 해당하는 detail 정보 api 요청하기
        } else {
            showToastMessage("서버로부터 응답받지 못했습니다")
        }
    }

    private fun initEvent() {
        // TODO LiveData를 통해 값을 받아와 view에게 넘겨주기
    }
}