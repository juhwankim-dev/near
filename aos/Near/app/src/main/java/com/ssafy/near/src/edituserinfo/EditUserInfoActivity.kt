package com.ssafy.near.src.edituserinfo

import android.content.Intent
import android.os.Bundle
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

        userViewModel.getUserInfo().observe(this, {
            if (it == null) {
                requestLogin()
            } else {
                binding.etNickname.editText!!.setText(it.nickname)
                binding.etEmail.editText!!.setText(it.email)
                oldInfo = it
            }
        })

        userViewModel.getCheckedNickname().observe(this, {
            isCheckedNickname = when (it) {
                true -> {
                    if (binding.etNickname.editText?.text.toString() == oldInfo.nickname) {
                        binding.etNickname.error = ""
                        binding.etNickname.helperText = ""
                        true
                    } else {
                        binding.etNickname.error = "이미 존재하는 닉네임입니다."
                        binding.etNickname.helperText = ""
                        false
                    }
                }
                false -> {
                    binding.etNickname.error = ""
                    binding.etNickname.helperText = "사용 가능한 닉네임입니다."
                    true
                }
            }
        })

        userViewModel.getCheckedEmail().observe(this, {
            isCheckedEmail = when (it) {
                true -> {
                    if (binding.etEmail.editText?.text.toString() == oldInfo.email) {
                        binding.etEmail.error = ""
                        binding.etEmail.helperText = "   "
                        true
                    } else {
                        binding.etEmail.error = "이미 존재하는 이메일입니다."
                        binding.etEmail.helperText = ""
                        false
                    }
                }
                false -> {
                    binding.etEmail.error = ""
                    binding.etEmail.helperText = "사용 가능한 이메일입니다."
                    true
                }
            }
        })

        userViewModel.getCheckedPw().observe(this, {
            isCheckedOldPw = when (it) {
                true -> {
                    binding.etOldPw.error = ""
                    binding.etOldPw.helperText = "비밀번호가 일치합니다."
                    true
                }
                false -> {
                    binding.etOldPw.error = "비밀번호가 일치하지 않습니다."
                    binding.etOldPw.helperText = ""
                    false
                }
            }
        })

        userViewModel.getUpdatedUser().observe(this, {
            when (it) {
                true -> {
                    showToastMessage("회원정보가 수정되었습니다.")
                    finish()
                }
                else -> {
                    showToastMessage("회원정보 수정에 실패했습니다. 다시 시도해주세요.")
                }
            }
        })
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
        binding.etNickname.editText?.addTextChangedListener {
            when {
                Validation.validateNickname(it.toString(),
                    binding.etNickname) -> checkDuplicatedNickname(it.toString())
                else -> isCheckedNickname = false
            }
        }

        binding.etEmail.editText?.addTextChangedListener {
            when {
                Validation.validateEmail(it.toString(),
                    binding.etEmail) -> checkDuplicatedEmail(it.toString())
                else -> isCheckedEmail = false
            }
        }

        binding.etOldPw.editText?.addTextChangedListener {
            when {
                Validation.validatePw(it.toString(), binding.etOldPw) -> checkPw(it.toString(),
                    sSharedPreferences.getUserToken())
                else -> isCheckedOldPw = false
            }
        }

        binding.etNewPw.editText?.addTextChangedListener {
            isCheckedNewPw = when {
                it.toString() == binding.etOldPw.editText?.text.toString() -> {
                    binding.etNewPw.error = "다른 비밀번호를 사용하세요."
                    false
                }
                else -> {
                    Validation.validatePw(it.toString(), binding.etNewPw)
                }
            }
            isCheckedConfirmNewPw =
                Validation.confirmPw(binding.etConfirmNewPw.editText?.text.toString(),
                    it.toString(),
                    binding.etConfirmNewPw)
        }

        binding.etConfirmNewPw.editText?.addTextChangedListener {
            isCheckedConfirmNewPw = Validation.confirmPw(it.toString(),
                binding.etNewPw.editText?.text.toString(),
                binding.etConfirmNewPw)
        }
    }

    private fun initEvent() {
        binding.btnSave.setOnClickListener {
            if (binding.etNewPw.editText?.text.toString().trim().isEmpty()
                && binding.etConfirmNewPw.editText?.text.toString().trim().isEmpty()) {
                isCheckedNewPw = true
                isCheckedConfirmNewPw = true
            }

            when {
                isCheckedNickname == false -> binding.etNickname.editText?.requestFocus()
                isCheckedEmail == false -> binding.etEmail.editText?.requestFocus()
                isCheckedOldPw == false -> binding.etOldPw.editText?.requestFocus()
                isCheckedNewPw == false -> binding.etNewPw.editText?.requestFocus()
                isCheckedConfirmNewPw == false -> binding.etConfirmNewPw.editText?.requestFocus()
                else -> {
                    val nickname = binding.etNickname.editText?.text.toString()
                    val email = binding.etEmail.editText?.text.toString()
                    val pw = if (binding.etNewPw.editText?.text.toString().trim().isEmpty()) {
                        binding.etOldPw.editText?.text.toString()
                    } else {
                        binding.etNewPw.editText?.text.toString()
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
            userViewModel.updateUser(id.toString(), nickname, email, pw)
        }
    }

    private fun requestLogin() {
        showToastMessage("로그인 정보가 만료되었습니다. 다시 로그인해주세요.")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}