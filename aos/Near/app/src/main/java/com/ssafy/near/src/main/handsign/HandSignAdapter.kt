package com.ssafy.near.src.main.handsign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemHandSignBinding
import com.ssafy.near.dto.HandSignInfo

class HandSignAdapter() : RecyclerView.Adapter<HandSignAdapter.HandSignViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener
    var handSignList = ArrayList<HandSignInfo>()

    inner class HandSignViewHolder(private val binding: ListItemHandSignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(handSignInfo: HandSignInfo) {
            binding.handSignInfo = handSignInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandSignViewHolder {
        var listItemBinding = ListItemHandSignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HandSignViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: HandSignViewHolder, position: Int) {
        holder.apply {
            bindInfo(handSignList[position])

            itemView.setOnClickListener {
                itemClickListener.onClick(handSignList[position])
            }
        }
    }

    override fun getItemCount(): Int = handSignList.size

    fun setInitList(list: List<HandSignInfo>) {
        handSignList.clear()
        handSignList.addAll(list)
    }

    interface ItemClickListener {
        fun onClick(handSignInfo: HandSignInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}