package com.dvipersquad.gallery.coreUI.mvvm

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dvipersquad.gallery.coreUI.BR
import timber.log.Timber

abstract class MvvmViewHolder<M : Any, VM : ViewHolderViewModel<M>>(
    private val viewModel: VM,
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setVariable(BR.viewModel, viewModel)
    }

    fun bind(model: M) {
        Timber.v("Bind item %s", model)
        viewModel.model = model
        viewModel.notifyChange()
        onViewModelUpdated(viewModel, model)
    }

    open fun onViewModelUpdated(viewModel: VM, model: M) {}
}
