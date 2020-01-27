package com.dvipersquad.gallery.feature.image.list

import com.dvipersquad.gallery.coreUI.coreUIModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val imageListModule = module {
    viewModel {
        ImageListViewModel(
            dispatchers = get()
        )
    }
}

val imageListFeatureModules = coreUIModules + imageListModule
