package com.dvipersquad.gallery.coreUI.mvvm


interface ClickableAdapter<T> {

    fun setItemClickListener(itemClickListener: ItemClickListener<T>)
}
