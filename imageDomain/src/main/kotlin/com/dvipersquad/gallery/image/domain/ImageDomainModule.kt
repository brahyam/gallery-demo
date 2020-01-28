package com.dvipersquad.gallery.image.domain

import com.dvipersquad.gallery.core.coreModule
import com.dvipersquad.gallery.rest.KEY_ANONYMOUS_HTTP_CLIENT
import com.dvipersquad.gallery.rest.KEY_BASE_URL
import com.dvipersquad.gallery.rest.restModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private val imageDomainModule = module {
    single<ImageRepository> {
        DefaultImageRepository(
            dispatchers = get(),
            imageApi = createImageApi(get())
        )
    }

    single {
        Retrofit.Builder()
            .baseUrl(get(named(KEY_BASE_URL)) as String)
            .addConverterFactory(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get(named(KEY_ANONYMOUS_HTTP_CLIENT)))
            .build()
    }
}

private fun createImageApi(retrofit: Retrofit) = retrofit.create(ImageApi::class.java)

val imageDomainModules = coreModule + restModule + imageDomainModule
