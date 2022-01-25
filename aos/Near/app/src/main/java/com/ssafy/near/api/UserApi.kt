package com.ssafy.near.api

import com.ssafy.near.dto.Duplication
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.dto.UserInfoResponse
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApi {
    @POST("api/sign/login")
    suspend fun login(
        @Query("type") @NotNull type: String,
        @Query("uid") @NotNull uid: String,
        @Query("password") @NotNull password: String,
    ): Response<SignResponse>


    @GET("api/sign/checkid")
    suspend fun checkId(@Query("uid") @NotNull uid: String): Response<Duplication>

    @GET("api/sign/nickname")
    suspend fun checkNickname(@Query("nickname") @NotNull nickname: String): Response<Duplication>

    @GET("api/sign/checkemail")
    suspend fun checkEmail(@Query("email") @NotNull email: String): Response<Duplication>

    @POST("api/sign/signup")
    suspend fun signUp(
        @Query("type") @NotNull type: String,
        @Query("uid") @NotNull uid: String,
        @Query("nickname") nickname: String,
        @Query("email") email: String,
        @Query("password") @NotNull password: String
    ): Response<SignResponse>


    @POST("api/sign/userInfo")
    suspend fun loadUserInfo(@Query("token") @NotNull token: String): Response<UserInfoResponse>


    @POST("api/modify/check")
    suspend fun checkPw(
        @Query("password") @NotNull password: String,
        @Query("token") @NotNull token: String,
    ): Response<Model<String>>

    @PUT("api/modify/user")
    suspend fun updateUser(
        @Query("id") @NotNull id: String,
        @Query("nickname") @NotNull nickname: String,
        @Query("email") @NotNull email: String,
        @Query("password") @NotNull pw: String
    ): Response<Model<String>>

    @PUT("api/modify/nickname")
    suspend fun updateNickname(
        @Query("id") @NotNull id: String,
        @Query("nickname") @NotNull nickname: String
    ): Response<Model<String>>

    @PUT("api/modify/email")
    suspend fun updateEmail(
        @Query("id") @NotNull id: String,
        @Query("email") @NotNull email: String
    ): Response<Model<String>>

    @PUT("api/modify/password")
    suspend fun updatePassword(
        @Query("id") @NotNull id: String,
        @Query("password") @NotNull pw: String
    ): Response<Model<String>>
}