package com.dvipersquad.gallery.core

import kotlin.coroutines.CoroutineContext

data class CoroutineDispatchers(
    val ui: CoroutineContext,
    val computation: CoroutineContext,
    val io: CoroutineContext
)
