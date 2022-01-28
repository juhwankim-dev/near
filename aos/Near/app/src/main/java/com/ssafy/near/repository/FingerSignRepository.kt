package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.dto.FingerSignInfo
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class FingerSignRepository {
    private val TAG = "FingerSignRepository"
    var _fingerSignList = MutableLiveData<List<FingerSignInfo>>()
        private set

    suspend fun loadFingerSignList() {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.fingerSignService.loadFingerSignList()
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _fingerSignList.postValue(response.body()!!.data)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}