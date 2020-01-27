package com.dvipersquad.gallery.coreUI.mvvm

open class ClickableItemViewModel<M : Any> : ViewHolderViewModel<M>() {

    lateinit var itemClickListener: ItemClickListener<M>

    open fun onItemClick() {
        itemClickListener.onItemClick(model)
    }
}
