package com.dvipersquad.gallery.coreUI.mvvm

import androidx.databinding.ViewDataBinding

abstract class ClickableViewHolder<M : Any, VM : ClickableItemViewModel<M>>(
    viewModel: VM,
    binding: ViewDataBinding,
    private val itemClickListener: ItemClickListener<M>
) : MvvmViewHolder<M, VM>(viewModel, binding) {

    override fun onViewModelUpdated(viewModel: VM, model: M) {
        viewModel.itemClickListener = itemClickListener
    }
}
