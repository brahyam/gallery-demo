package com.dvipersquad.gallery

import com.dvipersquad.gallery.navigation.NavControllerHolder
import com.dvipersquad.gallery.navigation.RootNavControllerHolder
import org.koin.dsl.module

internal val appModule = module {
    single<NavControllerHolder> {
        RootNavControllerHolder()
    }
}
