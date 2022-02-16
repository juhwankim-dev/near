package com.ssafy.near.src.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.repository.CertRepository

class CertViewModelFactory (private val certRepository : CertRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CertRepository::class.java).newInstance(certRepository)
    }
}