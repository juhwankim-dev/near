package com.ssafy.near.dto

import com.google.gson.annotations.SerializedName

data class Duplication(
    @SerializedName("data") val isDuplicated: Boolean,
    val msg: String,
    val output: Int
)