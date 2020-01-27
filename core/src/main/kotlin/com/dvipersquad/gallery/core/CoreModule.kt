package com.dvipersquad.gallery.core

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModule = module {
    single {
        CoroutineDispatchers(
            ui = Dispatchers.Main,
            computation = Dispatchers.Default,
            io = Dispatchers.IO
        )
    }
}
