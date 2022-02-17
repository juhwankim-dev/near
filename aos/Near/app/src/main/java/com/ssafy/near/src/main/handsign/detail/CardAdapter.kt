package com.ssafy.near.src.main.handsign.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemHandSignCardBinding
import com.ssafy.near.dto.HandSignInfo

class CardAdapter(var list: Array<HandSignInfo?>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(private val binding: ListItemHandSignCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(handSignInfo: HandSignInfo) {
            binding.handSignInfo = handSignInfo
            binding.isFirstCard = (layoutPosition == 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        var listItemHandSignCardBinding = ListItemHandSignCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(listItemHandSignCardBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindInfo(list[position]!!)
    }

    override fun getItemCount(): Int = list.size
}