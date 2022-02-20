package com.ssafy.near.src.main.game.wordquiz.playing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.databinding.ListItemWordResultBinding
import com.ssafy.near.dto.GameUser
import com.ssafy.near.dto.Result

class WordResultAdapter(var list: MutableList<Result>, val userList: ArrayList<GameUser>) : RecyclerView.Adapter<WordResultAdapter.WordViewHolder>() {

    inner class WordViewHolder(private val binding: ListItemWordResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(result: Result) {
            binding.result = result

            userList.forEach {
                if (result.name == it.name) {
                    when(it.avatar) {
                        0 -> binding.ivAvatar.setImageResource(R.drawable.img_avatar_1)
                        1 -> binding.ivAvatar.setImageResource(R.drawable.img_avatar_2)
                        2 -> binding.ivAvatar.setImageResource(R.drawable.img_avatar_3)
                    }
                }
            }

            when(layoutPosition) {
                0 -> binding.ivMedal.setImageResource(R.drawable.img_1st)
                1 -> binding.ivMedal.setImageResource(R.drawable.img_2nd)
                2 -> binding.ivMedal.setImageResource(R.drawable.img_3rd)
                else -> binding.ivMedal.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var listItemWordResultBinding = ListItemWordResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(listItemWordResultBinding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bindInfo(list[position]!!)
    }

    override fun getItemCount(): Int = list.size
}