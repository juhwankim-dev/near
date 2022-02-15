package com.ssafy.near.src.main.game.wordquiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ssafy.near.R
import com.ssafy.near.dto.Message
import com.ssafy.near.dto.MsgType
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent


class WordQuizViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private val TAG = "WordQuizViewModel"
    private val roomInfo = gameRepository._roomInfo
    private val roomList = gameRepository._roomList

    private val socketUrl = "wss://hoonycode2.loca.lt/ws-stomp/websocket"
    private val sendUrl = "/pub/room/message"
    private val client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, socketUrl)

    val question = arrayOf("서울시", "삼성")
    val images = arrayOf(
        arrayOf(R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012, R.drawable.ma_033, R.drawable.ma_006, R.drawable.ma_010, R.drawable.ma_040),
        arrayOf(R.drawable.ma_010, R.drawable.ma_020, R.drawable.ma_007, R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012)
    )

    private val scoreMap = HashMap<String, Int>() // map : user - score
    private val qNum = MutableLiveData(0)
    private val message = MutableLiveData<Message>()


    fun initUSer(userList: List<String>) {
        userList.forEach { name ->
            scoreMap[name] = 0
        }
    }

    fun updateUserScore(username: String, score: Int) {
        scoreMap[username]?.plus(score)
    }

    fun getUserScore(username: String): Int? {
        return scoreMap[username]
    }

    fun getRoomInfo(): LiveData<RoomInfo> {
        return roomInfo
    }

    fun getRoomList(): LiveData<MutableList<RoomInfo>> {
        return roomList
    }

    fun getQNum(): MutableLiveData<Int> {
        return qNum
    }

    fun getMessage(): MutableLiveData<Message> {
        return message
    }

    fun createRoom(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.createRoom(name)
        }
    }

    fun loadRooms() {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.loadRooms()
        }
    }

    fun nextQuiz() {
        qNum.postValue(qNum.value?.plus(1))
    }

    fun connect(roomId: String) {
        client.topic("/sub/chat/room/$roomId").subscribe { topicMessage ->
            message.postValue(Gson().fromJson(topicMessage.payload, Message::class.java))
            Log.i(TAG, " ${topicMessage.payload}")
        }
    }

    fun connectSocket() {
        client.connect()

        client.lifecycle().subscribe { lifecycleEvent->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED-> {
                    Log.i(TAG, "Stomp connection opened")
                }
                LifecycleEvent.Type.CLOSED-> {
                    Log.d(TAG, "Stomp connection closed")
                }
                LifecycleEvent.Type.ERROR-> {
                    Log.e(TAG, "Error", lifecycleEvent.exception)
                }
            }
        }
    }

    fun sendEntrance(roomId: String, sender: String) {
        val data = JSONObject().apply {
            put("type", MsgType.ENTER)
            put("roomId", roomId)
            put("sender", sender)
        }

        client.send(sendUrl, data.toString()).subscribe()
    }

    fun sendMessage(type: MsgType, roomId: String, sender: String, message: String) {
        val data = JSONObject().apply {
            put("type", type)
            put("roomId", roomId)
            put("sender", sender)
            put("message", message)
        }

        client.send(sendUrl, data.toString()).subscribe()
    }

    fun disconnect() {
        client.disconnect()
    }
}