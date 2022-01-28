package com.ssafy.near.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemHomeContentBinding

class ContentAdapter(var list: MutableList<String>) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    inner class ContentViewHolder(private val binding: ListItemHomeContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(s: String) {
            // TODO data binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        var listItemBinding = ListItemHomeContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bindInfo(list[position])
    }

    override fun getItemCount(): Int = list.size

}