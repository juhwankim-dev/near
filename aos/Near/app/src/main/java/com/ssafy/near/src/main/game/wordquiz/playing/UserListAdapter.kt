package com.ssafy.near.src.main.game.wordquiz.playing

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ssafy.near.R
import com.ssafy.near.databinding.ListItemWaitingUserBinding

class UserListAdapter(val userList: MutableList<String>) : BaseAdapter() {
    var myAvatar = 0

    override fun getCount(): Int = userList.size

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemBinding = ListItemWaitingUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        listItemBinding.tvUserName.text = userList[position]

        if(position == 0) {

        }

        when(position) {
            0 -> {
                when(myAvatar) {
                    0 -> listItemBinding.ivProfile.setImageResource(R.drawable.img_avatar_1)
                    1 -> listItemBinding.ivProfile.setImageResource(R.drawable.img_avatar_2)
                    2 -> listItemBinding.ivProfile.setImageResource(R.drawable.img_avatar_3)
                }
            }
        }

        return listItemBinding.root
    }
}