package com.ssafy.near.api

import com.ssafy.near.dto.FingerSignInfo
import com.ssafy.near.dto.ModelList
import retrofit2.Response
import retrofit2.http.GET

interface FingerSignApi {
    @GET("api/finger/")
    suspend fun loadFingerSignList(): Response<ModelList<FingerSignInfo>>
}