package com.ssafy.near.src.main.game.wordquiz.playing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemAvatarBinding

class AvatarAdapter(var list: Array<Int>) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    inner class AvatarViewHolder(private val binding: ListItemAvatarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(res: Int) {
            binding.ivAvatar.setImageResource(res)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        var listItemAvatarBinding = ListItemAvatarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvatarViewHolder(listItemAvatarBinding)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.bindInfo(list[position]!!)
    }

    override fun getItemCount(): Int = list.size
}