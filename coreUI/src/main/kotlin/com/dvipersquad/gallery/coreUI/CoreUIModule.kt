package com.dvipersquad.gallery.coreUI

import com.dvipersquad.gallery.core.coreModule
import com.dvipersquad.gallery.coreUI.mvvm.actions.SharedActionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreUIModule = module {
    viewModel {
        SharedActionViewModel(
            dispatchers = get()
        )
    }
}

val coreUIModules = coreUIModule + coreModule
