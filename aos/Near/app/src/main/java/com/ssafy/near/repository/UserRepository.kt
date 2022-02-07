package com.ssafy.near.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ssafy.near.config.ApplicationClass
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.dto.UserToken
import com.ssafy.near.util.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRepository {
    private val TAG = "UserRepository"
    var _signResponse = MutableLiveData<Model<UserToken>>()
        private set
    var _userInfo = MutableLiveData<UserInfo?>()
        private set

    var _isCheckedId = MutableLiveData<Boolean>()
        private set
    var _isCheckedNickname = MutableLiveData<Boolean>()
        private set
    var _isCheckedEmail = MutableLiveData<Boolean>()
        private set
    var _isCheckedPw = MutableLiveData<Boolean>()
        private set

    var _isUpdatedUser = MutableLiveData<Boolean>()
        private set
    var _isUpdatedNickname = MutableLiveData<Boolean>()
        private set
    var _isUpdatedEmail = MutableLiveData<Boolean>()
        private set
    var _isUpdatedPw = MutableLiveData<Boolean>()
        private set


    suspend fun login(uid: String, password: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["type"] = "none"
                req["uid"] = uid
                req["password"] = password

                RetrofitUtil.userService.login(req)
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val userResponse = response.body()!!
                    if (userResponse.output > 0) {
                        val userToken = userResponse.data
                        ApplicationClass.sSharedPreferences.addUser(userToken)
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

    suspend fun signUp(uid: String, nickname: String, email: String, password: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["type"] = "none"
                req["uid"] = uid
                req["nickname"] = nickname
                req["email"] = email
                req["password"] = password
                RetrofitUtil.userService.signUp(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _signResponse.postValue(response.body())
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun loadUserInfo(token: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["token"] = token
                RetrofitUtil.userService.loadUserInfo(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _userInfo.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedId(uid: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkId(uid)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isCheckedId.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedNickname(nickname: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkNickname(nickname)
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isCheckedNickname.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkDuplicatedEmail(email: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitUtil.userService.checkEmail(email)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isCheckedEmail.postValue(response.body()!!.data!!)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun checkPw(password: String, token: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["password"] = password
                req["token"] = token
                RetrofitUtil.userService.checkPw(req)
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isCheckedPw.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun updateUser(id: String, nickname: String, email: String, password: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["id"] = id
                req["nickname"] = nickname
                req["email"] = email
                req["password"] = password
                RetrofitUtil.userService.updateUser(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isUpdatedUser.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun updateNickname(id: String, nickname: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["id"] = id
                req["nickname"] = nickname
                RetrofitUtil.userService.updateNickname(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isUpdatedNickname.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun updateEmail(id: String, email: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["id"] = id
                req["email"] = email
                RetrofitUtil.userService.updateEmail(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isUpdatedEmail.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }

    suspend fun updatePw(id: String, password: String) {
        try {
            val response = withContext(Dispatchers.IO) {
                var req = mutableMapOf<String, String>()
                req["id"] = id
                req["password"] = password
                RetrofitUtil.userService.updatePassword(req)
            }
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _isUpdatedPw.postValue(response.body()!!.output == 1)
                }
            } else {
                Log.d(TAG, "onError: Error Code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "onFailure")
        }
    }
}