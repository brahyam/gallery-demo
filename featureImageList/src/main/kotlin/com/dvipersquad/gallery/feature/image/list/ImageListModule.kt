package com.dvipersquad.gallery.feature.image.list

import com.dvipersquad.gallery.coreUI.coreUIModules
import com.dvipersquad.gallery.image.usecases.imageUseCasesModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val imageListModule = module {
    viewModel {
        ImageListViewModel(
            dispatchers = get(),
            getImages = get()
        )
    }
}

val imageListFeatureModules = coreUIModules + imageUseCasesModules + imageListModule
