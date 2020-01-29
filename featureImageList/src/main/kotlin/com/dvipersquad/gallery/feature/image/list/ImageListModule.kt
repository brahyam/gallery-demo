package com.dvipersquad.gallery.feature.image.list

import com.dvipersquad.gallery.coreUI.coreUIModules
import com.dvipersquad.gallery.image.usecases.imageUseCasesModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val ITEM_ID = "237089773"

private val imageListModule = module {
    viewModel {
        ImageListViewModel(
            dispatchers = get(),
            itemId = ITEM_ID,
            getImages = get(),
            imageListNavigator = get()
        )
    }
}

val imageListFeatureModules = coreUIModules + imageUseCasesModules + imageListModule
