package com.dvipersquad.gallery.coreUI.mvvm.actions

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dvipersquad.gallery.core.CoroutineDispatchers
import com.dvipersquad.gallery.coreUI.mvvm.SingleLiveEvent
import kotlinx.coroutines.*

abstract class ActionViewModel(protected val dispatchers: CoroutineDispatchers) : ViewModel(),
    CoroutineScope {
    private val viewAction =
        SingleLiveEvent<ViewAction>()
    private var job = Job()
    override val coroutineContext = job + dispatchers.ui

    fun postAction(action: ViewAction) {
        viewAction.postValue(action)
    }

    @MainThread
    fun setAction(action: ViewAction) {
        viewAction.value = action
    }

    fun postActionDelayed(action: ViewAction, delay: Long) {
        launch(dispatchers.computation) {
            delay(delay)
            postAction(action)
        }
    }

    @CallSuper
    open fun observeAction(owner: LifecycleOwner, observer: (action: ViewAction) -> Unit) {
        viewAction.observe(owner, Observer { observer.invoke(it) })
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}
