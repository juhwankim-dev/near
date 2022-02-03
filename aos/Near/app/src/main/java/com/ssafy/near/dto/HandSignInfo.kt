package com.ssafy.near.dto

import java.io.Serializable

data class HandSignInfo(
    val handcontent_key: Long,
    val mean: String,
    val name: String,
    val explanation: String,
    val video_path: String,
    val image_path: String,
    val movement: String
) : Serializable