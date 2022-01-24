package com.ssafy.near.src.edituserinfo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import com.ssafy.near.util.Validation

class EditUserInfoActivity :
    BaseActivity<ActivityEditUserInfoBinding>(R.layout.activity_edit_user_info) {
    lateinit var userViewModel: UserViewModel
    var isCheckedNickname = false
    var isCheckedEmail = false
    var isCheckedOldPw = false
    var isCheckedNewPw = false
    var isCheckedConfirmNewPw = false
    var oldInfo = UserInfo()


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
                    when (binding.etNickname.editText?.text.toString()) {
                        oldInfo.nickname -> {
                            binding.etNickname.apply {
                                error = ""
                                helperText = ""
                            }
                            true
                        }
                        else -> {
                            binding.etNickname.apply {
                                error = "이미 존재하는 닉네임입니다."
                                helperText = ""
                            }
                            false
                        }
                    }
                }
                false -> {
                    binding.etNickname.apply {
                        error = ""
                        helperText = "사용 가능한 닉네임입니다."
                    }
                    true
                }
            }
        })

        userViewModel.getCheckedEmail().observe(this, {
            isCheckedEmail = when (it) {
                true -> {
                    when (binding.etEmail.editText?.text.toString()) {
                        oldInfo.email -> {
                            binding.etEmail.apply {
                                error = ""
                                helperText = "   "
                            }
                            true
                        }
                        else -> {
                            binding.etEmail.apply {
                                error = "이미 존재하는 이메일입니다."
                                helperText = ""
                            }
                            false
                        }
                    }
                }
                false -> {
                    binding.etEmail.apply {
                        error = ""
                        helperText = "사용 가능한 이메일입니다."
                    }
                    true
                }
            }
        })

        userViewModel.getCheckedPw().observe(this, {
            isCheckedOldPw = when (it) {
                true -> {
                    binding.etOldPw.apply {
                        error = ""
                        helperText = "비밀번호가 일치합니다."
                    }
                    true
                }
                false -> {
                    binding.etOldPw.apply {
                        error = "비밀번호가 일치하지 않습니다."
                        helperText = ""
                    }
                    false
                }
            }
        })
    }

    private fun initData() {
        val token = sSharedPreferences.getUserToken()
        if (token == "default") {
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
                    binding.etNewPw.error = ""
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

    private fun updateUserInfo() {

    }

    private fun requestLogin() {
        Toast.makeText(this, "로그인 정보가 만료되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}