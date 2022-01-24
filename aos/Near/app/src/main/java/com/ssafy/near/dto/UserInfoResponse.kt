package com.ssafy.near.dto

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("data") val userInfo: UserInfo,
)

data class UserInfo(
    val address: String,
    val addressDetail: String,
    val email: String,
    val name: String,
    val nickname: String,
    val password: String,
    val phone: String,
    val uid: String
)