package com.ssafy.near.dto

import com.google.gson.annotations.SerializedName

data class SignResponse(
    @SerializedName("data") val userToken: UserToken,
    val msg: String,
    val output: Int
)