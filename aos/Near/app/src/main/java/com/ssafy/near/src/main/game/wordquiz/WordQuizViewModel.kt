package com.ssafy.near.src.main.game.wordquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.R
import com.ssafy.near.dto.RoomInfo
import com.ssafy.near.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordQuizViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private val roomInfo = gameRepository._roomInfo


    val question = arrayOf("서울시", "삼성")
    val images = arrayOf(
        arrayOf(R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012, R.drawable.ma_033, R.drawable.ma_006, R.drawable.ma_010, R.drawable.ma_040),
        arrayOf(R.drawable.ma_010, R.drawable.ma_020, R.drawable.ma_007, R.drawable.ma_010, R.drawable.ma_024, R.drawable.ma_012)
    )
    var questionNum = 0


    fun getRoomInfo(): LiveData<RoomInfo> {
        return roomInfo
    }



    fun createRoom(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.createRoom(name)
        }
    }


}