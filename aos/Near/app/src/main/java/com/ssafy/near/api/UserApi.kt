package com.ssafy.near.api

import com.ssafy.near.dto.Duplication
import com.ssafy.near.dto.SignResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("api/sign/login")
    suspend fun login (
        @Query("type") @NotNull type: String,
        @Query("uid")  @NotNull uid : String,
        @Query("password") @NotNull password: String,
    ): Response<SignResponse>

    @GET("api/sign/checkid")
    suspend fun checkId(@Query("uid") @NotNull uid: String): Response<Duplication>

    @GET("api/sign/nickname")
    suspend fun checkNickname(@Query("nickname") @NotNull nickname: String): Response<Duplication>

    @GET("api/sign/checkemail")
    suspend fun checkEmail(@Query("email") @NotNull email: String): Response<Duplication>
}