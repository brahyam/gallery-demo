package com.dvipersquad.gallery.coreUI

import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

const val VERTICAL = LinearLayout.VERTICAL

class OffsetItemDecorator(
    private val orientation: Int = VERTICAL,
    private val offset: Int,
    private val sideOffset: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != 0) {
            if (orientation == VERTICAL) {
                outRect.top = offset
            } else {
                outRect.left = offset
            }
        }
        if (parent.getChildAdapterPosition(view) == 0) {
            if (orientation == VERTICAL) {
                outRect.top = sideOffset
            } else {
                outRect.left = sideOffset
            }
        }
        if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1) ?: 0) {
            if (orientation == VERTICAL) {
                outRect.bottom = sideOffset
            } else {
                outRect.right = sideOffset
            }
        }
    }
}
