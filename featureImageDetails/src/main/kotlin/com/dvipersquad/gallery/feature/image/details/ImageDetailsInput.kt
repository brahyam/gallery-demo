package com.dvipersquad.gallery.feature.image.details

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class ImageDetailsInput(
    val images: List<String>,
    val selectedImagePosition: Int
) : Serializable
