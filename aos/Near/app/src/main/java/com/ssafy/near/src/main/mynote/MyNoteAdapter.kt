package com.ssafy.near.src.main.mynote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemMyNoteBinding
import com.ssafy.near.dto.HandSignInfo

class MyNoteAdapter : RecyclerView.Adapter<MyNoteAdapter.MyNoteViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener
    var bookmarkList = ArrayList<HandSignInfo>()

    inner class MyNoteViewHolder(private val binding: ListItemMyNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(handSignInfo: HandSignInfo) {
            binding.handSignInfo = handSignInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewHolder {
        var listItemBinding = ListItemMyNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyNoteViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MyNoteViewHolder, position: Int) {
        holder.apply {
           bindInfo(bookmarkList[position])

            itemView.setOnClickListener {
                itemClickListener.onClick(bookmarkList[position])
            }
        }
    }

    override fun getItemCount(): Int = bookmarkList.size

    fun updateList(list: List<HandSignInfo>) {
        bookmarkList.clear()
        bookmarkList.addAll(list)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(handSignInfo: HandSignInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}