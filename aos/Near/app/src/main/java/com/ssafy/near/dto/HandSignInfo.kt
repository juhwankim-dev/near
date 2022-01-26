package com.ssafy.near.dto

data class HandSignInfo(
    val explanation: String,
    val handcotent_key: Long,
    val image_path: String,
    val name: String,
    val video_path: String
)