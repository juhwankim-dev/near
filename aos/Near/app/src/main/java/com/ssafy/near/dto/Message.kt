package com.ssafy.near.dto

import java.io.Serializable

data class Message(var type: MsgType, val roomId: String, val sender: String, val message: String) : Serializable

enum class MsgType {
    ENTER, OUT, TALK, START, END, CHANGE, ANSWER, NOTICE
}
