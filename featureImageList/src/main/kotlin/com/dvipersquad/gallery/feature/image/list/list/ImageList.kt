package com.dvipersquad.gallery.feature.image.list.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dvipersquad.gallery.feature.image.list.R

internal class ImageList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val imageListAdapter by lazy { ImageListAdapter() }

    init {
        layoutManager =
            StaggeredGridLayoutManager(resources.getInteger(R.integer.grid_rows), VERTICAL)
        adapter = imageListAdapter.apply {
            setHasStableIds(true)
        }
    }

    fun setItems(items: List<String>?) {
        if (items != null) {
            imageListAdapter.submitList(items)
        }
    }

    fun setImageClickListener(listener: ImageClickListener) =
        imageListAdapter.setImageClickListener(listener)

    interface ImageClickListener {
        fun onImageClicked(image: String)
    }
}
