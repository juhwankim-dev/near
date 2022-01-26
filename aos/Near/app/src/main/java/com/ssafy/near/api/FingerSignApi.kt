package com.ssafy.near.api

import com.ssafy.near.dto.FingerSignInfo
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.ModelList
import com.ssafy.near.dto.SampleResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FingerSignApi {
    @GET("api/finger/")
    suspend fun loadFingerSignList(): Response<ModelList<FingerSignInfo>>
}