package com.ssafy.near.src.edituserinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityEditUserInfoBinding
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.src.login.LoginActivity
import com.ssafy.near.util.SharedPreferencesUtil.Companion.DEFAULT_ID
import com.ssafy.near.util.SharedPreferencesUtil.Companion.DEFAULT_TOKEN
import com.ssafy.near.util.Validation
import com.ssafy.near.util.Validation.textViewSetting

class EditUserInfoActivity :
    BaseActivity<ActivityEditUserInfoBinding>(R.layout.activity_edit_user_info) {
    lateinit var userViewModel: UserViewModel
    lateinit var oldInfo: UserInfo
    var isCheckedNickname = false
    var isCheckedEmail = false
    var isCheckedOldPw = false
    var isCheckedNewPw = false
    var isCheckedConfirmNewPw = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initData()
        initValidation()
        initEvent()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getUserInfo().observe(this) {
            if (it == null) {
                requestLogin()
            } else {
                binding.etNickname.setText(it.nickname)
                binding.etEmail.setText(it.email)
                oldInfo = it
            }
        }

        userViewModel.getCheckedNickname().observe(this) {
            isCheckedNickname = when (it) {
                true -> {
                    if (binding.etNickname.text.toString() == oldInfo.nickname) {
                        textViewSetting(true, "", binding.tvNickNameError)
                        true
                    } else {
                        textViewSetting(false, "이미 존재하는 닉네임입니다.", binding.tvNickNameError)
                        false
                    }
                }
                false -> {
                    textViewSetting(true, "", binding.tvNickNameError)
                    true
                }
            }
        }

        userViewModel.getCheckedEmail().observe(this) {
            isCheckedEmail = when (it) {
                true -> {
                    if (binding.etEmail.text.toString() == oldInfo.email) {
                        textViewSetting(true, "", binding.tvEmailError)
                        true
                    } else {
                        textViewSetting(false, "이미 존재하는 이메일입니다.", binding.tvEmailError)
                        false
                    }
                }
                false -> {
                    textViewSetting(true, "", binding.tvEmailError)
                    true
                }
            }
        }

        userViewModel.getCheckedPw().observe(this) {
            isCheckedOldPw = when (it) {
                true -> {
                    textViewSetting(true, "", binding.tvOldPwError)
                    true
                }
                false -> {
                    textViewSetting(false, "비밀번호가 일치하지 않습니다.", binding.tvOldPwError)
                    false
                }
            }
        }

        userViewModel.getUpdatedUser().observe(this) {
            when (it) {
                true -> {
                    showToastMessage("회원정보가 수정되었습니다.")
                    finish()
                }
                else -> {
                    showToastMessage("회원정보 수정에 실패했습니다. 다시 시도해주세요.")
                }
            }
        }
    }

    private fun initData() {
        val token = sSharedPreferences.getUserToken()
        if (token == DEFAULT_TOKEN) {
            requestLogin()
        } else {
            userViewModel.loadUserInfo(token)
        }
    }

    private fun initValidation() {
        binding.etNickname.addTextChangedListener {
            when {
                Validation.validateNickname(it.toString(),
                    binding.tvNickNameError) -> checkDuplicatedNickname(it.toString())
                else -> isCheckedNickname = false
            }
        }

        binding.etEmail.addTextChangedListener {
            when {
                Validation.validateEmail(it.toString(),
                    binding.tvEmailError) -> checkDuplicatedEmail(it.toString())
                else -> isCheckedEmail = false
            }
        }

        binding.etOldPw.addTextChangedListener {
            when {
                Validation.validatePw(it.toString(), binding.tvOldPwError) -> checkPw(it.toString(),
                    sSharedPreferences.getUserToken())
                else -> isCheckedOldPw = false
            }
        }

        binding.etNewPw.addTextChangedListener {
            isCheckedNewPw = when {
                it.toString() == binding.etOldPw.text.toString() -> {
                    textViewSetting(false, "다른 비밀번호를 사용하세요.", binding.tvNewPwError)
                    false
                }
                else -> {
                    Validation.validatePw(it.toString(), binding.tvNewPwError)
                }
            }
            isCheckedConfirmNewPw =
                Validation.confirmPw(binding.etConfirmNewPw.text.toString(),
                    it.toString(),
                    binding.tvConfirmNewPwError)
        }

        binding.etConfirmNewPw.addTextChangedListener {
            isCheckedConfirmNewPw = Validation.confirmPw(it.toString(),
                binding.etNewPw.text.toString(),
                binding.tvConfirmNewPwError)
        }
    }

    private fun initEvent() {
        binding.btnSave.setOnClickListener {
            if (binding.etNewPw.text.toString().trim().isEmpty()
                && binding.etConfirmNewPw.text.toString().trim().isEmpty()) {
                isCheckedNewPw = true
                isCheckedConfirmNewPw = true
            }

            when {
                isCheckedNickname == false -> binding.etNickname.requestFocus()
                isCheckedEmail == false -> binding.etEmail.requestFocus()
                isCheckedOldPw == false -> binding.etOldPw.requestFocus()
                isCheckedNewPw == false -> binding.etNewPw.requestFocus()
                isCheckedConfirmNewPw == false -> binding.etConfirmNewPw.requestFocus()
                else -> {
                    val nickname = binding.etNickname.text.toString()
                    val email = binding.etEmail.text.toString()
                    val pw = if (binding.etNewPw.text.toString().trim().isEmpty()) {
                        binding.etOldPw.text.toString()
                    } else {
                        binding.etNewPw.text.toString()
                    }

                    updateUserInfo(nickname, email, pw)
                }
            }
        }
    }

    private fun checkDuplicatedNickname(nickname: String) {
        userViewModel.checkDuplicatedNickname(nickname)
    }

    private fun checkDuplicatedEmail(email: String) {
        userViewModel.checkDuplicatedEmail(email)
    }

    private fun checkPw(pw: String, token: String) {
        userViewModel.checkPw(pw, token)
    }

    private fun updateUserInfo(nickname: String, email: String, pw: String) {
        val id = sSharedPreferences.getUserId()

        if (id == DEFAULT_ID)
            requestLogin()
        else {
            userViewModel.updateUser(id, nickname, email, pw)
        }
    }

    private fun requestLogin() {
        showToastMessage("로그인 정보가 만료되었습니다. 다시 로그인해주세요.")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}