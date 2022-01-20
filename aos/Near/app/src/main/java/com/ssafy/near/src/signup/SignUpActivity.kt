package com.ssafy.near.src.signup

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivitySignUpBinding
import com.ssafy.near.util.Validation

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    var isCheckedId: Boolean = false
    var isCheckedNickname: Boolean = false
    var isCheckedEmail: Boolean = false
    var isCheckedPw: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initValidation()
        initEvent()
    }

    private fun initValidation() {
        binding.etId.editText?.addTextChangedListener {
            if (Validation.validateId(it.toString(), binding.etId)) {
                // 중복검사
            }
        }

        binding.etNickname.editText?.addTextChangedListener {
            if (Validation.validateNickname(it.toString(), binding.etNickname)) {
                // 중복검사
            }
        }

        binding.etEmail.editText?.addTextChangedListener {
            if (Validation.validateEmail(it.toString(), binding.etEmail) == false) {
                // 중복검사
            }
        }

        binding.etPw.editText?.addTextChangedListener {
            Validation.validatePw(it.toString(), binding.etPw)
        }

        binding.etConfirmPw.editText?.addTextChangedListener {
            isCheckedPw = Validation.confirmPw(it.toString(),
                binding.etPw.editText?.text.toString(),
                binding.etConfirmPw)
        }
    }

    private fun initEvent() {

    }
}