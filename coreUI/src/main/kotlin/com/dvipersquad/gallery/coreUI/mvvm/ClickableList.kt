package com.dvipersquad.gallery.coreUI.mvvm

interface ClickableList<T> {

    fun setItemClickListener(itemClickListener: ItemClickListener<T>)
}
