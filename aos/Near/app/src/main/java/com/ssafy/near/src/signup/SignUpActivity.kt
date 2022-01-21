package com.ssafy.near.src.signup

import android.os.Bundle
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

        userViewModel.getCheckedId().observe(this, {
            if (it) {
                isCheckedId = false
                binding.etId.error = "이미 존재하는 아이디입니다."
                binding.etId.helperText = ""
            } else {
                isCheckedId = true
                binding.etId.error = ""
                binding.etId.helperText = "사용 가능한 아이디입니다."
            }
        })

        userViewModel.getCheckedNickname().observe(this, {
            if (it) {
                isCheckedNickname = false
                binding.etNickname.error = "이미 존재하는 닉네임입니다."
                binding.etNickname.helperText = ""
            } else {
                isCheckedNickname = true
                binding.etNickname.error = ""
                binding.etNickname.helperText = "사용 가능한 닉네임입니다."
            }
        })

        userViewModel.getCheckedEmail().observe(this, {
            if (it) {
                isCheckedEmail = false
                binding.etEmail.error = "이미 존재하는 이메일입니다."
                binding.etEmail.helperText = ""
            } else {
                isCheckedEmail = true
                binding.etEmail.error = ""
                binding.etEmail.helperText = "사용 가능한 이메일입니다."
            }
        })

        userViewModel.getSignResponse().observe(this, { signResponse ->
            when {
                signResponse == null        -> Toast.makeText(this, "통신에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                signResponse.output != 1    -> Toast.makeText(this, signResponse.msg, Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }

    private fun initValidation() {
        binding.etId.editText?.addTextChangedListener {
            if (Validation.validateId(it.toString(), binding.etId)) {
                checkDuplicatedId(it.toString())
            } else {
                isCheckedId = false
            }
        }

        binding.etNickname.editText?.addTextChangedListener {
            if (Validation.validateNickname(it.toString(), binding.etNickname)) {
                checkDuplicatedNickname(it.toString())
            } else {
                isCheckedNickname = false
            }
        }

        binding.etEmail.editText?.addTextChangedListener {
            if (Validation.validateEmail(it.toString(), binding.etEmail)) {
                checkDuplicatedEmail(it.toString())
            } else {
                isCheckedEmail = false
            }
        }

        binding.etPw.editText?.addTextChangedListener {
            isCheckedPw = Validation.validatePw(it.toString(), binding.etPw)
            isCheckedConfirmPw = Validation.confirmPw(binding.etConfirmPw.editText?.text.toString(),
                it.toString(),
                binding.etConfirmPw)
        }

        binding.etConfirmPw.editText?.addTextChangedListener {
            isCheckedConfirmPw = Validation.confirmPw(it.toString(),
                binding.etPw.editText?.text.toString(),
                binding.etConfirmPw)
        }
    }

    private fun initEvent() {
        binding.btnSignUp.setOnClickListener {
            when {
                isCheckedId == false        -> binding.etId.editText?.requestFocus()
                isCheckedNickname == false  -> binding.etNickname.editText?.requestFocus()
                isCheckedEmail == false     -> binding.etEmail.editText?.requestFocus()
                isCheckedPw == false        -> binding.etPw.editText?.requestFocus()
                isCheckedConfirmPw == false -> binding.etConfirmPw.editText?.requestFocus()
                else -> {
                    val id = binding.etId.editText?.text.toString()
                    val nickname = binding.etNickname.editText?.text.toString()
                    val email = binding.etEmail.editText?.text.toString()
                    val pw = binding.etPw.editText?.text.toString()

                    signUp(id, nickname, email, pw)
                }
            }
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