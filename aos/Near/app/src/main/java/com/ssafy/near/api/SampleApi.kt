package com.ssafy.near.api

import com.ssafy.near.dto.SampleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SampleApi {
    @GET("search/repositories")
    suspend fun selectSamples(@Query("q") q: String, @Query("page") page: String): Response<SampleResponse>
}