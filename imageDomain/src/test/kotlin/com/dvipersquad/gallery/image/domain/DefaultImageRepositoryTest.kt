package com.dvipersquad.gallery.image.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvipersquad.gallery.test.TestUtils
import com.dvipersquad.gallery.test.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
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

    @Mock
    private lateinit var imageApi: ImageApi

    private lateinit var toBeTested: DefaultImageRepository

    @Before
    fun setUp() {
        runBlocking {
            given(imageApi.getImages(any(String::class.java))).willReturn(createMockImageResponse())
        }
        toBeTested = DefaultImageRepository(
            TestUtils.dispatchers,
            imageApi
        )
    }

    @Test
    fun `test getImages calls api`() {
        runBlocking {
            val mockItemId = "mockItemId"
            toBeTested.getImages(mockItemId)
            then(imageApi).should().getImages(eq(mockItemId))
        }
    }

    private fun createMockImageResponse() =
        ImageResponse(listOf(ImageItemResponse("image1"), ImageItemResponse("image2")))

}
