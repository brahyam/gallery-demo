package com.dvipersquad.gallery.rest

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
const val KEY_BASE_URL = "BASE_URL"
const val KEY_ANONYMOUS_HTTP_CLIENT = "ANONYMOUS_HTTP_CLIENT"

val restModule = module {

    factory {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    single(named(KEY_ANONYMOUS_HTTP_CLIENT)) {
        createAnonymousHttpClient(
            httpLoggingInterceptor = get()
        )
    }

    single(named(KEY_BASE_URL)) {
        BuildConfig.BASE_URL
    }

    single {
        Moshi.Builder()
            .build()
    }

    factory<Converter.Factory> {
        MoshiConverterFactory.create(get())
    }
}

private fun createAnonymousHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    }.build()
