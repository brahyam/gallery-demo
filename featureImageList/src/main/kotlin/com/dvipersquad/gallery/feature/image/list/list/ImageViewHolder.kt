package com.dvipersquad.gallery.feature.image.list.list

import android.view.ViewGroup
import com.dvipersquad.gallery.coreUI.inflateBinding
import com.dvipersquad.gallery.coreUI.mvvm.MvvmViewHolder
import com.dvipersquad.gallery.feature.image.list.R

internal class ImageViewHolder(
    viewModel: ImageViewModel,
    parent: ViewGroup,
    private val imageClickListener: ImageList.ImageClickListener
) : MvvmViewHolder<String, ImageViewModel>(
    viewModel,
    parent.inflateBinding(R.layout.image_item, false)
) {

    override fun onViewModelUpdated(viewModel: ImageViewModel, model: String) {
        super.onViewModelUpdated(viewModel, model)
        viewModel.imageClickListener = imageClickListener
    }
}
