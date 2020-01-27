package com.dvipersquad.gallery.coreUI.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dvipersquad.gallery.coreUI.clearFocus
import com.dvipersquad.gallery.coreUI.hideKeyboard
import com.dvipersquad.gallery.coreUI.mvvm.actions.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.emptyParametersHolder
import kotlin.reflect.KClass

abstract class MvvmFragment<VB : ViewDataBinding, VM : ActionViewModel>(clazz: KClass<VM>) :
    Fragment() {

    lateinit var binding: VB

    val actionViewModel: VM by viewModel(clazz = clazz, parameters = this.getParameters())

    private val sharedActionViewModel: SharedActionViewModel by sharedViewModel()

    protected open fun getParameters(): ParametersDefinition = {
        emptyParametersHolder()
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, actionViewModel)
        return binding.root
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        actionViewModel.observeAction(viewLifecycleOwner) { action ->
            if (handleAction(action).not()) {
                if (action is DialogViewAction || action is CommonViewEvent) {
                    sharedActionViewModel.postAction(action)
                } else {
                    throw IllegalStateException("Action not handled: $action")
                }
            }
        }
    }

    /**
     * @return true if the action was handled by the implementer
     */
    @CallSuper
    protected open fun handleAction(action: ViewAction) = when (action) {
        is CommonViewEvent.GoBack -> {
            findNavController().navigateUp()
            true
        }
        is CommonViewEvent.HideKeyboard -> {
            activity?.apply {
                hideKeyboard()
                clearFocus()
            }
            true
        }
        else -> false
    }

    abstract fun getLayoutId(): Int
}
