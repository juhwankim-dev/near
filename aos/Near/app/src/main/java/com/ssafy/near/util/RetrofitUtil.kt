package com.ssafy.near.util

import com.ssafy.near.api.FingerSignApi
import com.ssafy.near.api.GameApi
import com.ssafy.near.api.HandSignApi
import com.ssafy.near.api.UserApi
import com.ssafy.near.config.ApplicationClass

class RetrofitUtil {
    companion object {
        val userService = ApplicationClass.retrofit.create(UserApi::class.java)
        val fingerSignService = ApplicationClass.retrofit.create(FingerSignApi::class.java)
        val handSignService = ApplicationClass.retrofit.create(HandSignApi::class.java)
        val gameService = ApplicationClass.retrofit.create(GameApi::class.java)
    }
}