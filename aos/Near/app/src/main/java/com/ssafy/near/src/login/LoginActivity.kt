package com.ssafy.near.src.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
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
    private var isIdEmpty: Boolean? = true
    private var isPwEmpty: Boolean? = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getSignResponse().observe(this) { signResponse ->
            when {
                signResponse == null        -> showToastMessage("통신에 문제가 발생하였습니다.")
                signResponse.output != 1    -> showToastMessage(signResponse.msg)
                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    fun initView() {
        binding.tvSignUp.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId?.text.toString().trim()
            val pw = binding.etPw?.text.toString().trim()

            if (id == "") {
                binding.etId?.requestFocus()
                showToastMessage("아이디를 입력하세요.")
                return@setOnClickListener;
            }
            if (pw == "") {
                binding.etPw?.requestFocus()
                showToastMessage("비밀번호를 입력하세요.")
                return@setOnClickListener;
            }

            login(id, pw)
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.etId.addTextChangedListener {
            isIdEmpty = it.toString().isEmpty()
            updateBtnState()
        }

        binding.etPw.addTextChangedListener {
            isPwEmpty = it.toString().isEmpty()
            updateBtnState()
        }
    }

    private fun login(id: String, pw: String) {
        userViewModel.login(id, pw)
    }

    private fun updateBtnState() {
        if(isIdEmpty == false && isPwEmpty == false) {
            binding.btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.temp_blue))
            binding.btnLogin.isClickable = true
        } else {
            binding.btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.gray_btn_disabled))
            binding.btnLogin.isClickable = false
        }
    }
}
