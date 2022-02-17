package com.ssafy.near.src.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.Model
import com.ssafy.near.repository.CertRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CertViewModel(private val certRepository : CertRepository) : ViewModel() {
    private val certNumber = certRepository._certNumber
    private val mailedResult = certRepository._mailedResult

    fun getCertNumber(): MutableLiveData<Model<String>> {
        return certNumber
    }

    fun getmailedResult(): MutableLiveData<Model<String>> {
        return mailedResult
    }

    fun sendCertNumber(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            certRepository.sendCertNumber(email)
        }
    }

    fun sendMail(address: String, message: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            certRepository.sendMail(address, message, title)
        }
    }
}