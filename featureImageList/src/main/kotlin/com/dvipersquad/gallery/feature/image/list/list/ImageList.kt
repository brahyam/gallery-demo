package com.dvipersquad.gallery.feature.image.list.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvipersquad.gallery.coreUI.ItemOffsetDecoration
import com.dvipersquad.gallery.feature.image.list.R

internal class ImageList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val imageListAdapter by lazy { ImageListAdapter() }

    init {
        layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.grid_rows))
        adapter = imageListAdapter.apply {
            setHasStableIds(true)
        }
        addItemDecoration(ItemOffsetDecoration(context, R.dimen.spacing_xxsmall))
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
