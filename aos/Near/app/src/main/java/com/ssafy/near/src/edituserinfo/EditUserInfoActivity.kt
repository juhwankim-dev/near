package com.ssafy.near.src.edituserinfo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityEditUserInfoBinding
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.src.login.LoginActivity

class EditUserInfoActivity : BaseActivity<ActivityEditUserInfoBinding>(R.layout.activity_edit_user_info) {
    lateinit var userViewModel: UserViewModel
    var oldInfo = UserInfo()
    var newInfo = UserInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initData()
        initEvent()

    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getUserInfo().observe(this, {
            if (it == null) {
                requestLogin()
            } else {
                binding.etNickname.editText!!.setText(it.nickname)
                binding.etEmail.editText!!.setText(it.email)
                oldInfo = it
            }
        })
    }

    private fun initData() {
        val token = ApplicationClass.sSharedPreferences.getToken()
        if (token == "") {
            requestLogin()
        } else {
            userViewModel.loadUserInfo(token!!)
        }
    }

    private fun initEvent() {
        binding.btnSave.setOnClickListener {

        }
    }

    private fun updateUserInfo() {

    }

    private fun requestLogin() {
        Toast.makeText(this, "로그인 정보가 만료되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}