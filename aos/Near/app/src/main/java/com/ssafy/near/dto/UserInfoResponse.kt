package com.ssafy.near.dto

data class UserInfoResponse(
    val data: Data,
    val msg: String,
    val output: Int
)

data class Data(
    val address: String,
    val addressDetail: String,
    val email: String,
    val name: String,
    val nickname: String,
    val password: String,
    val phone: String,
    val uid: String
)