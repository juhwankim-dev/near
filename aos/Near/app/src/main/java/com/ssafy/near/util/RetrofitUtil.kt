package com.ssafy.near.util

import com.ssafy.near.api.FingerSignApi
import com.ssafy.near.api.HandSignApi
import com.ssafy.near.api.SampleApi
import com.ssafy.near.api.UserApi
import com.ssafy.near.config.ApplicationClass

class RetrofitUtil {
    companion object {
        val sampleService = ApplicationClass.retrofit.create(SampleApi::class.java) // TODO 샘플보고 필요한 service 작성할 것
        val userService = ApplicationClass.retrofit.create(UserApi::class.java)
        val fingerSignService = ApplicationClass.retrofit.create(FingerSignApi::class.java)
        val handSignService = ApplicationClass.retrofit.create(HandSignApi::class.java)
    }
}