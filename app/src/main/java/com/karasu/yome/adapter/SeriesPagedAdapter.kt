package com.karasu.yome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karasu.yome.databinding.ListItemBinding
import com.karasu.yome.model.User
import com.karasu.yome.network.dto.SeriesDto
import com.karasu.yome.utils.Constants

class SeriesPagedAdapter :
    PagingDataAdapter<SeriesDto, SeriesPagedAdapter.MyViewHolder>(diffCallback) {

    var onItemClick: ((SeriesDto) -> Unit)? = null

    inner class MyViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition)!!)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SeriesDto>() {
            override fun areItemsTheSame(oldItem: SeriesDto, newItem: SeriesDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeriesDto, newItem: SeriesDto): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // getItem() is from PagingDataAdapter
        val currentItem = getItem(position)

        holder.binding.apply {
            itemTitle.text = "${currentItem?.name}"

            val url = "${Constants.BASE_URL}Image/series-cover?seriesId=${currentItem?.id}"
            itemCover.load(url) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}