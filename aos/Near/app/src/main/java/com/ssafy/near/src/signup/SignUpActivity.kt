package com.ssafy.near.src.signup

import android.os.Bundle
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
    var isCheckedId: Boolean = false
    var isCheckedNickname: Boolean = false
    var isCheckedEmail: Boolean = false
    var isCheckedPw: Boolean = false

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
            Validation.validatePw(it.toString(), binding.etPw)
            isCheckedPw = Validation.confirmPw(binding.etConfirmPw.editText?.text.toString(),
                it.toString(),
                binding.etConfirmPw)
        }

        binding.etConfirmPw.editText?.addTextChangedListener {
            isCheckedPw = Validation.confirmPw(it.toString(),
                binding.etPw.editText?.text.toString(),
                binding.etConfirmPw)
        }
    }

    private fun initEvent() {

    }

    fun checkDuplicatedId(id: String) {
        userViewModel.checkDuplicatedId(id)
    }

    fun checkDuplicatedNickname(nickname: String) {
        userViewModel.checkDuplicatedNickname(nickname)
    }

    fun checkDuplicatedEmail(email: String) {
        userViewModel.checkDuplicatedEmail(email)
    }
}