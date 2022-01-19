package com.ssafy.near.src.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityLoginBinding
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.main.MainActivity
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        initEvent()
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.editText?.text.toString().trim()
            val pw = binding.etPw.editText?.text.toString().trim()

            if (id == "") {
                binding.etId.editText?.requestFocus()
                Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            if (pw == "") {
                binding.etPw.editText?.requestFocus()
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            login("none", id, pw)
        }
    }

    private fun login(type: String, uid: String, pw: String) {
        userViewModel.login(type, uid, pw)
        userViewModel.getSignResponse().observe(this, { signResponse ->
            if (signResponse == null) {
                Toast.makeText(this, "통신에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            } else if (signResponse.output != 1) {
                Toast.makeText(this, signResponse.msg, Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}