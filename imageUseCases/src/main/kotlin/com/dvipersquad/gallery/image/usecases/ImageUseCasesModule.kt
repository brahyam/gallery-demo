package com.dvipersquad.gallery.image.usecases

import com.dvipersquad.gallery.image.domain.imageDomainModules
import org.koin.dsl.module

private val imageUseCasesModule = module {
    factory {
        GetImagesUseCase(
            dispatchers = get(),
            imageRepository = get()
        )
    }
}

val imageUseCasesModules = imageDomainModules + imageUseCasesModule
