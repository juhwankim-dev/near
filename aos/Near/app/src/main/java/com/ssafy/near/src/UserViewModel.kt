package com.ssafy.near.src

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val signResponse = userRepository._signResponse
    private val userInfo = userRepository._userInfo
    private val isCheckedId = userRepository._isCheckedId
    private val isCheckedNickname = userRepository._isCheckedNickname
    private val isCheckedEmail = userRepository._isCheckedEmail
    private val isCheckedPw = userRepository._isCheckedPw


    fun getSignResponse(): LiveData<SignResponse> {
        return signResponse
    }

    fun getUserInfo(): LiveData<UserInfo?> {
        return userInfo
    }

    fun getCheckedId(): LiveData<Boolean> {
        return isCheckedId
    }

    fun getCheckedNickname(): LiveData<Boolean> {
        return isCheckedNickname
    }

    fun getCheckedEmail(): LiveData<Boolean> {
        return isCheckedEmail
    }

    fun getCheckedPw(): LiveData<Boolean> {
        return isCheckedPw
    }

    fun login(id: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(id, pw)
        }
    }

    fun signUp(id: String, nickname: String, email: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.signUp(id, nickname, email, pw)
        }
    }

    fun loadUserInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.loadUserInfo(token)
        }
    }

    fun checkDuplicatedId(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedId(id)
        }
    }

    fun checkDuplicatedNickname(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedNickname(nickname)
        }
    }

    fun checkDuplicatedEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedEmail(email)
        }
    }

    fun checkPw(pw: String, token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkPw(pw, token)
        }
    }
}