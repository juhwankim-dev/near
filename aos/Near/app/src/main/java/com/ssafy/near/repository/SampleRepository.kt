package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.SampleResponse
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SampleRepository {
    private val TAG = "SampleRepository_juhwan"
    var _sampleResponse = MutableLiveData<SampleResponse>()

    suspend fun selectSamples(page: Int) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.sampleService.selectSamples("Java", page.toString())
            }

            if(response.isSuccessful) {
                if(response.body() != null) {
                    _sampleResponse.postValue(response.body())
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}