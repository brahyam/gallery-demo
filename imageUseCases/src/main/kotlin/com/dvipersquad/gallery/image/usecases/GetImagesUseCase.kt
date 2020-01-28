package com.dvipersquad.gallery.image.usecases

import com.dvipersquad.gallery.core.CoroutineDispatchers
import com.dvipersquad.gallery.core.Result
import com.dvipersquad.gallery.image.domain.ImageRepository
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetImagesUseCase(
    private val dispatchers: CoroutineDispatchers,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(itemId: String) = withContext(dispatchers.computation) {
        try {
            val result = imageRepository.getImages(itemId)
            Result.Success(result)
        } catch (exception: Exception) {
            Timber.e(exception, "There was a problem getting the images")
            Result.Error<List<String>>(
                message = exception.message ?: "Error getting images. Try again later",
                cause = exception
            )
        }
    }
}
