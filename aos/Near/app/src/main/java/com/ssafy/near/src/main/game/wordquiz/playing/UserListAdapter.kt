package com.ssafy.near.src.main.game.wordquiz.playing

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.ssafy.near.R
import com.ssafy.near.databinding.ListItemWaitingUserBinding

class UserListAdapter(val userList: MutableList<Pair<String, Int>>) : BaseAdapter() {
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
        listItemBinding.apply {
            tvUserName.text = userList[position].first
            when(userList[position].second) {
                0 -> ivProfile.setImageResource(R.drawable.img_avatar_1)
                1 -> ivProfile.setImageResource(R.drawable.img_avatar_2)
                2 -> ivProfile.setImageResource(R.drawable.img_avatar_3)
            }
        }

        return listItemBinding.root
    }
}