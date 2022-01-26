package com.ssafy.near.src

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.dto.UserToken
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

    private val isUpdatedUser = userRepository._isUpdatedUser
    private val isUpdatedNickname = userRepository._isUpdatedNickname
    private val isUpdatedEmail = userRepository._isUpdatedEmail
    private val isUpdatedPw = userRepository._isUpdatedPw


    fun getSignResponse(): LiveData<Model<UserToken>> {
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

    fun getUpdatedUser(): LiveData<Boolean> {
        return isUpdatedUser
    }

    fun getUpdatedNickname(): LiveData<Boolean> {
        return isUpdatedNickname
    }

    fun getUpdatedEmail(): LiveData<Boolean> {
        return isUpdatedEmail
    }

    fun getUpdatedPw(): LiveData<Boolean> {
        return isUpdatedPw
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

    fun updateUser(id: String, nickname: String, email: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(id, nickname, email, pw)
        }
    }

    fun updateNickname(id: String, nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateNickname(id, nickname)
        }
    }

    fun updateEmail(id: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateEmail(id, email)
        }
    }

    fun updatePw(id: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updatePw(id, pw)
        }
    }
}