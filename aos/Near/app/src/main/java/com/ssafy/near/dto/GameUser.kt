package com.ssafy.near.dto

data class GameUser(val name: String, var avatar: Int) {
    var isReady: Boolean = false

    fun updateStatus() {
        isReady = !isReady
    }
}
