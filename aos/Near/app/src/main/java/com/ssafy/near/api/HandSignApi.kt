package com.ssafy.near.api

import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.dto.ModelList
import retrofit2.Response
import retrofit2.http.GET

interface HandSignApi {
    @GET("api/hand/")
    suspend fun loadHandSignList(): Response<ModelList<HandSignInfo>>
}