package com.ssafy.near.dto

data class HandSignInfo(
    val handcotent_key: Long,
    val mean: String,
    val name: String,
    val explanation: String,
    val video_path: String,
    val image_path: String
)