package com.dvipersquad.gallery.feature.image.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageDetailsModule = module {
    viewModel { (imageDetailsInput: ImageDetailsInput) ->
        ImageDetailsViewModel(
            dispatchers = get(),
            imageDetailsInput = imageDetailsInput
        )
    }
}
