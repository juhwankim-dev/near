package com.ssafy.near.api

import com.ssafy.near.dto.Model
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.dto.UserToken
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @FormUrlEncoded
    @POST("api/sign/login")
    suspend fun login(@FieldMap req: MutableMap<String, String>): Response<Model<UserToken>>

    @GET("api/sign/checkid")
    suspend fun checkId(@Query("uid") @NotNull uid: String): Response<Model<Boolean>>

    @GET("api/sign/nickname")
    suspend fun checkNickname(@Query("nickname") @NotNull nickname: String): Response<Model<Boolean>>

    @GET("api/sign/checkemail")
    suspend fun checkEmail(@Query("email") @NotNull email: String): Response<Model<Boolean>>

    @FormUrlEncoded
    @POST("api/sign/signup")
    suspend fun signUp(@FieldMap req: MutableMap<String, String>): Response<Model<UserToken>>

    @FormUrlEncoded
    @POST("api/sign/userInfo")
    suspend fun loadUserInfo(@FieldMap req: MutableMap<String, String>): Response<Model<UserInfo>>

    @FormUrlEncoded
    @POST("api/modify/check")
    suspend fun checkPw(@FieldMap req: MutableMap<String, String>): Response<Model<String>>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "api/modify/user", hasBody = true)
    suspend fun updateUser(@FieldMap req: MutableMap<String, String>): Response<Model<String>>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "api/modify/nickname", hasBody = true)
    suspend fun updateNickname(@FieldMap req: MutableMap<String, String>): Response<Model<String>>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "api/modify/email", hasBody = true)
    suspend fun updateEmail(@FieldMap req: MutableMap<String, String>): Response<Model<String>>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "api/modify/password", hasBody = true)
    suspend fun updatePassword(@FieldMap req: MutableMap<String, String>): Response<Model<String>>
}