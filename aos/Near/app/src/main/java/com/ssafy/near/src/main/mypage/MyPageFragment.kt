package com.ssafy.near.src.main.mypage

import android.os.Bundle
import android.view.View
import com.ssafy.near.R
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {

    }

    private fun initEvent() {
        binding.tvMemberModify.setOnClickListener {

        }

        binding.tvSetting.setOnClickListener {

        }

        binding.tvMyNote.setOnClickListener {

        }
    }
}