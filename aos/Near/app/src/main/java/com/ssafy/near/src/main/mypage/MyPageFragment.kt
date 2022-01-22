package com.ssafy.near.src.main.mypage

import android.os.Bundle
import android.view.View
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.tvNickName.text = sSharedPreferences.getUserId()
    }

    private fun initEvent() {

        binding.btnLogout.setOnClickListener {
            sSharedPreferences.deleteUser()
            binding.tvNickName.text = "로그인이 필요한\n서비스 입니다."
            binding.btnLogout.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
        }
    }
}