package com.ssafy.near.dto

data class ModelList<T>(
    val data: List<T>,
    val msg: String,
    val output: Int
)