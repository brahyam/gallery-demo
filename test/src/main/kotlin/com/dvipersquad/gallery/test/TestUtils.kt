package com.dvipersquad.gallery.test

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.dvipersquad.gallery.core.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import org.mockito.Mockito

object TestUtils {
    val lifecycleOwner: LifecycleOwner = object : LifecycleOwner {

        private val registry = init()

        // Creates a LifecycleRegistry in RESUMED state.
        private fun init(): LifecycleRegistry {
            val registry = LifecycleRegistry(this)
            registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            return registry
        }

        override fun getLifecycle(): Lifecycle {
            return registry
        }
    }

    val dispatchers =
        CoroutineDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined)
}

fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

fun <T> any(): T = Mockito.any<T>()
