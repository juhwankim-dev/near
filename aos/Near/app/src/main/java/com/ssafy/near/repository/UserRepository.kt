package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {
    private val TAG = "UserRepository"
    var _signResponse = MutableLiveData<SignResponse>()
        private set

    suspend fun login(type: String, uid: String, pw: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.login(type, uid, pw, "")
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val userResponse = response.body()!!
                    if (userResponse.output > 0) {
                        val token = userResponse.userToken
                        ApplicationClass.sSharedPreferences.addUser(token)
                    }
                    _signResponse.postValue(userResponse)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}