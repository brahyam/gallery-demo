package com.dvipersquad.gallery.feature.image.list.list

import com.dvipersquad.gallery.coreUI.mvvm.ClickableItemViewModel

internal class ImageViewModel : ClickableItemViewModel<String>() {
    lateinit var imageClickListener: ImageList.ImageClickListener

    fun onImageClicked() {
        imageClickListener.onImageClicked(model)
    }
}
