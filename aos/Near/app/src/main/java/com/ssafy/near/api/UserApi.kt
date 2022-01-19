package com.ssafy.near.api

import com.ssafy.near.dto.SignResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("api/sign/login")
    suspend fun login (
        @Query("type") @NotNull type: String,
        @Query("uid")  @NotNull uid : String,
        @Query("password") @NotNull password: String,
    ): Response<SignResponse>
}