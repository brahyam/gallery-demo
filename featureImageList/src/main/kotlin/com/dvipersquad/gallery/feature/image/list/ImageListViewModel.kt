package com.dvipersquad.gallery.feature.image.list

import androidx.lifecycle.MutableLiveData
import com.dvipersquad.gallery.core.CoroutineDispatchers
import com.dvipersquad.gallery.coreUI.mvvm.actions.ActionViewModel
import timber.log.Timber

class ImageListViewModel(
    dispatchers: CoroutineDispatchers
) : ActionViewModel(dispatchers) {

    val images = MutableLiveData<List<String>>().apply {
        value = listOf(
            "https://i.ebayimg.com/00/s/MTA2NlgxNjAw/z/TaoAAOSwjkdZ57Jm/\$_27.jpg",
            "https://i.ebayimg.com/00/s/MTE5MlgxNjAw/z/1S8AAOSwUYNZ5j3W/\$_27.jpg",
            "https://i.ebayimg.com/00/s/MTEwM1gxNjAw/z/F1EAAOSwa~BYVs1H/\$_27.jpg"
        )
    }

    fun onImageClicked(image: String) {
        Timber.d("image $image clicked")
    }
}
