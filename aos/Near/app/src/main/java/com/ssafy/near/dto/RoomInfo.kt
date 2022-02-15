package com.ssafy.near.dto

import java.io.Serializable

data class RoomInfo(
    val name: String,
    val roomId: String,
    val host: String,
    val userCount: Int,
    val userList: List<String>
) : Serializable