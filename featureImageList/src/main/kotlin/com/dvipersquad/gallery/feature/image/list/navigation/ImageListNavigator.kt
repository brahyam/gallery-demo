package com.dvipersquad.gallery.feature.image.list.navigation

interface ImageListNavigator {
    fun navigateToImageDetails(images: List<String>, selectedImagePosition: Int)
}
