package com.dvipersquad.gallery.navigation

import com.dvipersquad.gallery.RootNavigationDirections
import com.dvipersquad.gallery.feature.image.details.ImageDetailsInput
import com.dvipersquad.gallery.feature.image.list.navigation.ImageListNavigator

class AppImageListNavigator(
    private val navControllerHolder: NavControllerHolder
) : ImageListNavigator {
    override fun navigateToImageDetails(images: List<String>, selectedImagePosition: Int) {
        val imageDetailsInput =
            ImageDetailsInput(
                images = images,
                selectedImagePosition = selectedImagePosition
            )
        val action = RootNavigationDirections.actionToImageDetails(imageDetailsInput)
        navControllerHolder.get()
            .navigate(action)
    }
}
