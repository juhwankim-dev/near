package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.dto.Model
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CertRepository {
    private val TAG = "CertRepository"
    var _certNumber = MutableLiveData<Model<String>>()
        private set

    suspend fun sendCertNumber(email: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.certService.sendCertNumber(email)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _certNumber.postValue(response.body()!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}