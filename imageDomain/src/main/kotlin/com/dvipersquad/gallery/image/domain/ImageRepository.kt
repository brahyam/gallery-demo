package com.dvipersquad.gallery.image.domain

import com.dvipersquad.gallery.core.CoroutineDispatchers
import kotlinx.coroutines.withContext

interface ImageRepository {
    suspend fun getImages(itemId: String): List<String>
}

internal class DefaultImageRepository(
    private val dispatchers: CoroutineDispatchers,
    private val imageApi: ImageApi
) : ImageRepository {
    override suspend fun getImages(itemId: String): List<String> =
        withContext(dispatchers.io) {
            val result = imageApi.getImages(itemId)
            return@withContext result.images.map { it.toModel() }
        }
}

private fun ImageItemResponse.toModel() = "https://${uri}_27.jpg"

