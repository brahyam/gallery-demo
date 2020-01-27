package com.dvipersquad.gallery.core

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val value: T) : Result<T>()
    data class Error<out T : Any>(val message: String, val cause: Exception? = null) : Result<T>()
}
