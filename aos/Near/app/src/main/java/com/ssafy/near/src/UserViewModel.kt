package com.ssafy.near.src

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val signResponse = userRepository._signResponse

    fun getSignResponse(): LiveData<SignResponse> {
        return signResponse
    }

    fun login(type: String, id: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(type, id, pw)
        }
    }
}