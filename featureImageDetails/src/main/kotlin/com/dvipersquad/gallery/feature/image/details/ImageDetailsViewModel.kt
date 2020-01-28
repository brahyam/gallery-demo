package com.dvipersquad.gallery.feature.image.details

import androidx.lifecycle.MutableLiveData
import com.dvipersquad.gallery.core.CoroutineDispatchers
import com.dvipersquad.gallery.coreUI.mvvm.actions.ActionViewModel

class ImageDetailsViewModel(
    dispatchers: CoroutineDispatchers,
    val imageDetailsInput: ImageDetailsInput
) : ActionViewModel(dispatchers) {

    val selectedPosition =
        MutableLiveData<Int>().apply { value = imageDetailsInput.selectedImagePosition }
}
