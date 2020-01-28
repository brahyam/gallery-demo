package com.dvipersquad.gallery.feature.image.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvipersquad.gallery.image.usecases.GetImagesUseCase
import com.dvipersquad.gallery.test.TestUtils
import com.dvipersquad.gallery.test.any
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
    private lateinit var getImagesUseCase: GetImagesUseCase

    private lateinit var toBeTested: ImageListViewModel

    @Before
    fun setUp() {
        toBeTested = ImageListViewModel(
            TestUtils.dispatchers,
            getImagesUseCase
        )
    }

    @Test
    fun `test images are loaded on init`() {
        runBlocking {
            then(getImagesUseCase).should().invoke(any(String::class.java))
        }
    }
}
