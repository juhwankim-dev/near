package com.ssafy.near.dto

data class UserInfo(
    val uid: String,
    val email: String,
    val nickname: String,
    val password: String,
    val name: String,
    val phone: String,
    val address: String,
    val addressDetail: String,
)