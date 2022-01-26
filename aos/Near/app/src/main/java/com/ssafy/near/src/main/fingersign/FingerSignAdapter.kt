package com.ssafy.near.src.main.fingersign

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.near.databinding.ListItemFingerSignBinding
import com.ssafy.near.dto.FingerSignInfo

class FingerSignAdapter : RecyclerView.Adapter<FingerSignAdapter.FingerSignViewHolder>(),
    Filterable {
    private lateinit var itemClickListner: ItemClickListener
    private var fingerSignList: ArrayList<FingerSignInfo> = ArrayList()
    private var fingerSignListFiltered: ArrayList<FingerSignInfo> = ArrayList()

    inner class FingerSignViewHolder(private val binding: ListItemFingerSignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(fingerSignInfo: FingerSignInfo) {
            binding.fingerSignInfo = fingerSignInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FingerSignViewHolder {
        var listItemBinding = ListItemFingerSignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FingerSignViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: FingerSignViewHolder, position: Int) {
        holder.apply {
            bindInfo(fingerSignListFiltered[position])

            //클릭연결
            itemView.setOnClickListener{
                itemClickListner.onClick(fingerSignListFiltered[position])
            }
        }
    }

    override fun getItemCount(): Int = fingerSignListFiltered.size

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val category = constraint?.toString()?.toInt()
                val filteredList = ArrayList<FingerSignInfo>()

                fingerSignListFiltered = if(category == 0 || category == 1) {
                    fingerSignList.filter{it.category == category}.forEach { filteredList.add(it) }
                    filteredList
                } else {
                    fingerSignList
                }

                return FilterResults().apply { values = fingerSignListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                fingerSignListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<FingerSignInfo>
                notifyDataSetChanged()
            }
        }
    }

    fun setInitList(list: List<FingerSignInfo>) {
        fingerSignList.clear()
        fingerSignListFiltered.clear()
        fingerSignList.addAll(list)
        fingerSignListFiltered.addAll(list)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(fingerSignInfo: FingerSignInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}