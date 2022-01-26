package com.ssafy.near.dto


data class Model<T>(
    val data: T,
    val msg: String,
    val output: Int
)
