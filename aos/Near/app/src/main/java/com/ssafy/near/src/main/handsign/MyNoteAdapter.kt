package com.ssafy.near.src.main.handsign

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.R
import com.ssafy.near.databinding.ListItemMyNoteBinding
import com.ssafy.near.dto.HandSignInfo

class MyNoteAdapter : RecyclerView.Adapter<MyNoteAdapter.MyNoteViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var hasUncheckedListener: HasUncheckedListener
    var bookmarkList = ArrayList<HandSignInfo>()

    inner class MyNoteViewHolder(private val binding: ListItemMyNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(handSignInfo: HandSignInfo) {
            binding.handSignInfo = handSignInfo
            updateCheckState(handSignInfo.isChecked, binding.ivCheck)

            binding.ivCheck.setOnClickListener {
                when(handSignInfo.isChecked) {
                    true -> {
                        bookmarkList[layoutPosition].isChecked = false
                        updateCheckState(false, binding.ivCheck)
                    }

                    false -> {
                        bookmarkList[layoutPosition].isChecked = true
                        updateCheckState(true, binding.ivCheck)
                    }
                }

                hasUncheckedListener.onClick(bookmarkList.any{ h: HandSignInfo -> !h.isChecked })
            }
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

    fun selectAll() {
        when(bookmarkList.any{ h: HandSignInfo -> !h.isChecked }) {
            true -> {
                for(i in 0 until bookmarkList.size) {
                    bookmarkList[i].isChecked = true
                }
            }

            false -> {
                for(i in 0 until bookmarkList.size) {
                    bookmarkList[i].isChecked = false
                }
            }
        }

        notifyDataSetChanged()
    }

    private fun updateCheckState(isChecked: Boolean, imageView: ImageView) {
        when(isChecked) {
            true -> {
                imageView.imageTintList = ColorStateList.valueOf(imageView.resources.getColor(R.color.temp_blue))
            }

            false -> {
                imageView.imageTintList = ColorStateList.valueOf(imageView.resources.getColor(R.color.gray_unchecked))
            }
        }
    }

    interface ItemClickListener {
        fun onClick(handSignInfo: HandSignInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface HasUncheckedListener {
        fun onClick(hasUnchecked: Boolean)
    }

    fun setHasUncheckedListener(hasUncheckedListener: HasUncheckedListener) {
        this.hasUncheckedListener = hasUncheckedListener
    }
}