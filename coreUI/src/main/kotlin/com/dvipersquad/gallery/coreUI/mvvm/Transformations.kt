package com.dvipersquad.gallery.coreUI.mvvm

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

@MainThread
fun <T> merge(vararg sources: LiveData<T>): LiveData<T> {
    val result = MediatorLiveData<T>()
    sources.forEach {
        result.addSource(it) { value ->
            result.value = value
        }
    }
    return result
}

@MainThread
fun <A, B, C> combineLatest(
    a: LiveData<A>,
    b: LiveData<B>,
    mapFunction: (A, B) -> C
): LiveData<C> {
    return MediatorLiveData<C>().apply {
        var lastA: A? = null
        var lastB: B? = null

        addSource(a) {
            if (it == null && value != null) value = null
            lastA = it
            if (lastA != null && lastB != null) value = mapFunction(lastA!!, lastB!!)
        }

        addSource(b) {
            if (it == null && value != null) value = null
            lastB = it
            if (lastA != null && lastB != null) value = mapFunction(lastA!!, lastB!!)
        }
    }
}

@MainThread
fun <X, Y> mapSkipNulls(
    source: LiveData<X>,
    mapFunction: (X) -> Y
): MutableLiveData<Y> {
    return MediatorLiveData<Y>().apply {
        addSource(source) { x ->
            x ?: return@addSource
            value = mapFunction(x)
        }
    }
}

@MainThread
fun <X, Y> mapUnique(
    source: LiveData<X>,
    predicate: (X) -> Boolean = { true },
    mapFunction: (X) -> Y
): LiveData<Y> {
    var previous: X? = null
    val result = MediatorLiveData<Y>()
    result.addSource(source) { x ->
        if (predicate.invoke(x)) {
            if (previous != x) { // Check if input is the same
                previous = x
                val value = mapFunction.invoke(x)
                if (result.value != value) { // Check if output is the same
                    result.value = value
                }
            }
        }
    }
    return result
}

@MainThread
fun <X, Y> map(
    source: LiveData<X>,
    default: Y? = null,
    predicate: (X) -> Boolean = { true },
    mapFunction: (X) -> Y
): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    default?.let { result.value = it }
    result.addSource(source) { x ->
        if (predicate.invoke(x)) {
            result.value = mapFunction.invoke(x)
        }
    }
    return result
}
