package com.ssafy.near.src.main.game.wordquiz

import android.content.Intent
import android.os.Bundle
import com.ssafy.near.R
import com.ssafy.near.config.BaseActivity
import com.ssafy.near.databinding.ActivityRoomBinding
import com.ssafy.near.dto.RoomInfo


class RoomActivity : BaseActivity<ActivityRoomBinding>(R.layout.activity_room) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        //초기 실행화면 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_room)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_room, RoomListFragment())
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_room, RoomListFragment())
                .commit()
        }
    }

    fun enterRoom(roomInfo: RoomInfo) {
        val intent = Intent(this, WordQuizActivity::class.java)
        intent.putExtra("roomInfo", roomInfo)
        startActivity(intent)
    }
}