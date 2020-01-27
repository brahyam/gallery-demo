package com.dvipersquad.gallery.coreUI.mvvm

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.DialogFragment
import com.dvipersquad.gallery.coreUI.mvvm.actions.ActionViewModel
import com.dvipersquad.gallery.coreUI.mvvm.actions.ViewAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.emptyParametersHolder
import kotlin.reflect.KClass

abstract class MvvmDialogFragment<VB : ViewDataBinding, VM : ActionViewModel>(clazz: KClass<VM>) :
    DialogFragment() {

    lateinit var binding: VB
    val actionViewModel: VM by viewModel(clazz = clazz, parameters = this.getParameters())

    protected open fun getParameters(): ParametersDefinition = {
        emptyParametersHolder()
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        actionViewModel.observeAction(this) { action ->
            check(!handleAction(action).not()) { "Action not handled: $action" }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)
        dialogTitleStringId?.let { builder.setTitle(it) }
        dialogMessageStringId?.let { builder.setMessage(it) }
        val view = LayoutInflater.from(context)
            .inflate(customViewLayoutId, null)

        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, actionViewModel)
        builder.setView(view)

        builder.setPositiveButton(positiveButtonText) { _, _ -> positiveButtonClicked() }
        builder.setNegativeButton(negativeButtonText) { _, _ -> negativeButtonClicked() }
        return builder.create()
    }

    protected abstract fun positiveButtonClicked()

    protected open fun negativeButtonClicked() {}

    /**
     * @return true if the action was handled by the implementer
     */
    protected open fun handleAction(action: ViewAction) = false

    protected open val dialogMessageStringId: Int? = null
    protected open val dialogTitleStringId: Int? = null
    protected abstract val customViewLayoutId: Int
    protected open val positiveButtonText: Int = android.R.string.ok
    protected open val negativeButtonText: Int = android.R.string.cancel
}
