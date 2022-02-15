package com.ssafy.near.util

import android.widget.TextView
import java.util.regex.Pattern

object Validation {
    const val ID = "^[a-zA-Z0-9]*\$"
    const val NICKNAME = "^[a-zA-Z가-힣0-9]*\$"
    const val PW = "^[a-zA-Z0-9!@#$%^&*]*\$"

    fun validateId(id: String, textView: TextView): Boolean {
        return when {
            Pattern.matches(ID, id) == false -> {
                textViewSetting(false, "사용할 수 없는 문자를 포함하고 있습니다.", textView)
                false
            }
            id.length < 6 || id.length > 15 -> {
                textViewSetting(false, "아이디는 6 ~ 15자입니다.", textView)
                false
            }
            else -> {
                textViewSetting(true, "", textView)
                true
            }
        }
    }

    fun validateNickname(nickname: String, textView: TextView): Boolean {
        if (Pattern.matches(NICKNAME, nickname) == false) {
            textViewSetting(false, "사용할 수 없는 문자를 포함하고 있습니다.", textView)
            return false
        }
        if (nickname.isEmpty() || nickname.length > 6) {
            textViewSetting(false, "닉네임은 1 ~ 6자입니다.", textView)
            return false
        }
        textViewSetting(true, "", textView)
        return true
    }

    fun validateEmail(email: String, textView: TextView): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS

        return when {
            email.trim().isEmpty() -> {
                textViewSetting(false, "이메일을 입력하십시오.", textView)
                false
            }
            pattern.matcher(email).matches() == false -> {
                textViewSetting(false, "올바른 이메일 형식이 아닙니다.", textView)
                false
            }
            else -> {
                textViewSetting(true, "", textView)
                true
            }
        }
    }

    fun validatePw(pw: String, textView: TextView): Boolean {
        return when {
            Pattern.matches(PW, pw) == false -> {
                textViewSetting(false, "사용할 수 없는 문자를 포함하고 있습니다.", textView)
                false
            }
            pw.length < 8 || pw.length > 20 -> {
                textViewSetting(false, "비밀번호는 8 ~ 20자입니다.", textView)
                false
            }
            else -> {
                textViewSetting(true, "", textView)
                true
            }
        }
    }

    fun confirmPw(checkedPw: String, pw: String, textView: TextView): Boolean {
        return when {
            checkedPw == pw -> {
                textViewSetting(true, "", textView)
                true
            }
            checkedPw.isEmpty() -> {
                textView.text = "" // 도연이가 짠 코드가 이렇게 되어있어서 isValid를 true로 줬음
                textViewSetting(true, "", textView)
                false
            }
            else -> {
                textViewSetting(false, "비밀번호가 일치하지 않습니다.", textView)
                false
            }
        }
    }

    fun textViewSetting(isValid: Boolean, errorMessage: String, textView: TextView) {
        when(isValid) {
            true -> textView.text = ""
            false -> textView.text = errorMessage
        }
    }
}