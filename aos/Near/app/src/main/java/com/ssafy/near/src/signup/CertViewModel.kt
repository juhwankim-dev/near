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

    fun getCertNumber(): MutableLiveData<Model<String>> {
        return certNumber
    }

    fun sendCertNumber(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            certRepository.sendCertNumber(email)
        }
    }
}