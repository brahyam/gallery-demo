package com.dvipersquad.gallery.feature.image.list

import androidx.lifecycle.MutableLiveData
import com.dvipersquad.gallery.core.CoroutineDispatchers
import com.dvipersquad.gallery.core.Result
import com.dvipersquad.gallery.coreUI.mvvm.actions.ActionViewModel
import com.dvipersquad.gallery.coreUI.mvvm.actions.CommonViewEvent
import com.dvipersquad.gallery.image.usecases.GetImagesUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class ImageListViewModel(
    dispatchers: CoroutineDispatchers,
    val getImages: GetImagesUseCase
) : ActionViewModel(dispatchers) {

    val images = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    init {
        loadImages()
    }

    private fun loadImages() {
        isLoading.postValue(true)
        launch(dispatchers.computation) {
            when (val result = getImages("237089773")) { //TODO: Inject item id
                is Result.Success -> {
                    images.postValue(result.value)
                }
                is Result.Error -> {
                    postAction(CommonViewEvent.ShowErrorSnackBar(result.message))
                }
            }
            isLoading.postValue(false)
        }
    }

    fun onImageClicked(image: String) {
        Timber.d("image $image clicked")
    }
}
