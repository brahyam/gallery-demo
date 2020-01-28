package com.dvipersquad.gallery.image.domain

import retrofit2.http.GET
import retrofit2.http.Path

internal interface ImageApi {
    @GET("a/{itemId}")
    suspend fun getImages(@Path("itemId") itemId: String): ImageResponse
}
