package com.ssafy.near.src.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivitySignUpBinding
import com.ssafy.near.util.Validation
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    var checkedId: Boolean = false
    var checkedNickname: Boolean = false
    var checkedEmail: Boolean = false
    var checkedPw: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initValidation()
    }

    private fun initValidation() {
        binding.etId.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (Pattern.matches(Validation.ID, s) == false) {
                    binding.etId.error = "사용할 수 없는 문자를 포함하고 있습니다."
                    checkedId = false
                    return

                } else if (s.length < 6 || s.length > 15) {
                    binding.etId.error = "아이디는 6 ~ 15자입니다."
                    checkedId = false
                    return

                } else {
                    binding.etId.error = ""
                }

                // 중복검사
            }
        })

        binding.etNickname.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty() || s.length > 6) {
                    binding.etNickname.error = "닉네임은 1 ~ 6자입니다."
                    checkedNickname = false
                    return

                } else if (Pattern.matches(Validation.NICKNAME, s) == false) {
                    binding.etNickname.error = "사용할 수 없는 문자를 포함하고 있습니다."
                    checkedNickname = false
                    return

                } else {
                    binding.etNickname.error = ""
                }

                // 중복검사
            }
        })

        binding.etEmail.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val pattern = android.util.Patterns.EMAIL_ADDRESS

                if (pattern.matcher(s).matches() == false) {
                    binding.etEmail.error = "올바른 이메일 형식이 아닙니다."
                    checkedEmail = false
                    return

                } else {
                    binding.etEmail.error = ""
                }

                // 중복검사
            }
        })
    }
}