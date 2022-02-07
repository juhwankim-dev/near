package com.ssafy.near.src.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivitySignUpBinding
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.util.Validation

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    lateinit var userViewModel: UserViewModel
    var isCheckedId = false
    var isCheckedNickname = false
    var isCheckedEmail = false
    var isCheckedPw = false
    var isCheckedConfirmPw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initValidation()
        initEvent()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getCheckedId().observe(this) {
            if (it) {
                isCheckedId = false
                Validation.textViewSetting(false, "이미 존재하는 아이디입니다.", binding.tvIdError)
            } else {
                isCheckedId = true
                Validation.textViewSetting(true, "", binding.tvIdError)
            }
        }

        userViewModel.getCheckedNickname().observe(this) {
            if (it) {
                isCheckedNickname = false
                Validation.textViewSetting(false, "이미 존재하는 닉네임입니다.", binding.tvNickNameError)
            } else {
                isCheckedNickname = true
                Validation.textViewSetting(true, "", binding.tvNickNameError)
            }
        }

        userViewModel.getCheckedEmail().observe(this) {
            if (it) {
                isCheckedEmail = false
                Validation.textViewSetting(false, "이미 존재하는 이메일입니다.", binding.tvEmailError)

                binding.btnEmailAuth.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.gray_btn_disabled))
                binding.btnEmailAuth.isClickable = false
            } else {
                isCheckedEmail = true
                Validation.textViewSetting(true, "", binding.tvEmailError)

                binding.btnEmailAuth.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.temp_blue))
                binding.btnEmailAuth.isClickable = true
            }
        }

        userViewModel.getSignResponse().observe(this) { signResponse ->
            when {
                signResponse == null -> showToastMessage("통신에 문제가 발생하였습니다.")
                signResponse.output != 1 -> showToastMessage(signResponse.msg)
                else -> {
                    showToastMessage("회원가입 성공")
                    finish()
                }
            }
        }
    }

    private fun initValidation() {
        binding.etId.addTextChangedListener {
            if (Validation.validateId(it.toString(), binding.tvIdError)) {
                checkDuplicatedId(it.toString())
            } else {
                isCheckedId = false
            }
        }

        binding.etNickname.addTextChangedListener {
            if (Validation.validateNickname(it.toString(), binding.tvNickNameError)) {
                checkDuplicatedNickname(it.toString())
            } else {
                isCheckedNickname = false
            }
        }

        binding.etEmail.addTextChangedListener {
            if (Validation.validateEmail(it.toString(), binding.tvEmailError)) {
                checkDuplicatedEmail(it.toString())
            } else {
                isCheckedEmail = false
            }
        }

        binding.etPw.addTextChangedListener {
            isCheckedPw = Validation.validatePw(it.toString(), binding.tvPwError)
            isCheckedConfirmPw = Validation.confirmPw(binding.etConfirmPw.text.toString(),
                it.toString(),
                binding.tvConfirmPwError)
        }

        binding.etConfirmPw.addTextChangedListener {
            isCheckedConfirmPw = Validation.confirmPw(it.toString(),
                binding.etPw.text.toString(),
                binding.tvConfirmPwError)
        }
    }

    private fun initEvent() {
        binding.btnSignUp.setOnClickListener {
            when {
                isCheckedId == false        -> binding.etId.requestFocus()
                isCheckedNickname == false  -> binding.etNickname.requestFocus()
                isCheckedEmail == false     -> binding.etEmail.requestFocus()
                isCheckedPw == false        -> binding.etPw.requestFocus()
                isCheckedConfirmPw == false -> binding.etConfirmPw.requestFocus()
                else -> {
                    val id = binding.etId.text.toString()
                    val nickname = binding.etNickname.text.toString()
                    val email = binding.etEmail.text.toString()
                    val pw = binding.etPw.text.toString()

                    signUp(id, nickname, email, pw)
                }
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun checkDuplicatedId(id: String) {
        userViewModel.checkDuplicatedId(id)
    }

    private fun checkDuplicatedNickname(nickname: String) {
        userViewModel.checkDuplicatedNickname(nickname)
    }

    private fun checkDuplicatedEmail(email: String) {
        userViewModel.checkDuplicatedEmail(email)
    }

    private fun signUp(id: String, nickname: String, email: String, pw: String) {
        userViewModel.signUp(id, nickname, email, pw)
    }
}