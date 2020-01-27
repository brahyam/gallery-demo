package com.dvipersquad.gallery.coreUI.mvvm

import androidx.databinding.BaseObservable

open class ViewHolderViewModel<M : Any> : BaseObservable() {

    lateinit var model: M
}
