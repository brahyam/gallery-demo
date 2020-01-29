package com.dvipersquad.gallery.feature.image.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvipersquad.gallery.feature.image.list.navigation.ImageListNavigator
import com.dvipersquad.gallery.image.usecases.GetImagesUseCase
import com.dvipersquad.gallery.test.TestUtils
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mockItemId = "mockId"

    @Mock
    private lateinit var getImagesUseCase: GetImagesUseCase

    @Mock
    private lateinit var imageListNavigator: ImageListNavigator

    private lateinit var toBeTested: ImageListViewModel

    @Before
    fun setUp() {
        toBeTested = ImageListViewModel(
            TestUtils.dispatchers,
            mockItemId,
            getImagesUseCase,
            imageListNavigator
        )
    }

    @Test
    fun `test images are loaded on init`() {
        runBlocking {
            then(getImagesUseCase).should().invoke(eq(mockItemId))
        }
    }

    @Test
    fun `test click on images navigates to details`() {
        val mockImages = listOf("image1", "image2", "image3")
        toBeTested.images.postValue(mockImages)
        toBeTested.onImageClicked(mockImages[0])
        then(imageListNavigator).should().navigateToImageDetails(eq(mockImages), eq(0))
    }


}
