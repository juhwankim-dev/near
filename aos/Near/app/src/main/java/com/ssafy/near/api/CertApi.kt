package com.ssafy.near.api

import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.ModelList
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.*

interface CertApi {
    @GET("api/certification/{email}")
    suspend fun sendCertNumber(@Path("email") @NotNull email: String): Response<Model<String>>

    @FormUrlEncoded
    @POST("api/certification/mail")
    suspend fun sendMail(@FieldMap req: MutableMap<String, String>): Response<Model<String>>
}