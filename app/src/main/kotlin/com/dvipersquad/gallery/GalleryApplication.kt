package com.dvipersquad.gallery

import android.app.Application
import com.dvipersquad.gallery.feature.image.details.imageDetailsModule
import com.dvipersquad.gallery.feature.image.list.imageListFeatureModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GalleryApplication : Application() {
    private val modules = imageListFeatureModules +
            imageDetailsModule +
            appModule


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@GalleryApplication)
            modules(modules.distinct())
        }
    }
}
