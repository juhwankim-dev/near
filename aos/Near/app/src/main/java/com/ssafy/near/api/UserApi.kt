package com.ssafy.near.api

import com.ssafy.near.dto.SignResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("api/sign/login")
    fun login (
        @Query("type") @NotNull type: String,
        @Query("uid")  @NotNull uid : String,
        @Query("pass") @NotNull pass: String,
        @Query("token") token: String,
    ): Response<SignResponse>
}