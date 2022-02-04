package com.ssafy.near.src.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityLoginBinding
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.main.MainActivity
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.src.signup.SignUpActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initEvent()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getSignResponse().observe(this, { signResponse ->
            when {
                signResponse == null        -> showToastMessage("통신에 문제가 발생하였습니다.")
                signResponse.output != 1    -> showToastMessage(signResponse.msg)
                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.editText?.text.toString().trim()
            val pw = binding.etPw.editText?.text.toString().trim()

            if (id == "") {
                binding.etId.editText?.requestFocus()
                showToastMessage("아이디를 입력하세요.")
                return@setOnClickListener;
            }
            if (pw == "") {
                binding.etPw.editText?.requestFocus()
                showToastMessage("비밀번호를 입력하세요.")
                return@setOnClickListener;
            }

            login(id, pw)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(id: String, pw: String) {
        userViewModel.login(id, pw)
    }
}
