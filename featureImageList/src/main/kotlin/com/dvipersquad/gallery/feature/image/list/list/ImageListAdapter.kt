package com.dvipersquad.gallery.feature.image.list.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class ImageListAdapter : ListAdapter<String, ImageViewHolder>(ItemCallback()) {

    private lateinit var imageClickListener: ImageList.ImageClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageViewModel(), parent, imageClickListener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setImageClickListener(listener: ImageList.ImageClickListener) {
        imageClickListener = listener
    }

    class ItemCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem != newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode()
            .toLong()
    }
}
