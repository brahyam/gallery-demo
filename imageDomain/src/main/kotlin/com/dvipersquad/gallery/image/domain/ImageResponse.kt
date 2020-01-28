package com.dvipersquad.gallery.image.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val images: List<ImageItemResponse>
)

@JsonClass(generateAdapter = true)
data class ImageItemResponse(
    val uri: String
)
