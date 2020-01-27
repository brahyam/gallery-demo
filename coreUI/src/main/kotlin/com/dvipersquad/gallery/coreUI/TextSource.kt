package com.dvipersquad.gallery.coreUI

import android.content.res.Resources
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class TextSource(private val block: (Resources) -> String) {

    fun retrieveString(resources: Resources): String = block(resources)

    private object EmptyString : TextSource({ "" })

    private data class StringResource(private val resId: Int, private val args: List<Any?>) :
        TextSource({
            it.getString(resId, *args.map { arg ->
                if (arg is TextSource) {
                    arg.retrieveString(it)
                } else {
                    arg
                }
            }.toTypedArray())
        })

    private data class PluralResource(
        private val resId: Int,
        private val quantity: Int,
        private val args: List<Any?>
    ) : TextSource({
        it.getQuantityString(resId, quantity, *args.toTypedArray())
    })

    private data class NumberLiteral(private val value: Number) : TextSource({ value.toString() })
    private data class StringLiteral(private val value: String) : TextSource({ value })

    companion object {

        fun empty(): TextSource =
            EmptyString

        fun plural(@PluralsRes resId: Int, quantity: Int): TextSource =
            plural(
                resId, quantity, quantity
            )

        fun plural(@PluralsRes resId: Int, quantity: Int, vararg args: Any?): TextSource =
            PluralResource(
                resId, quantity, args.asList()
            )

        fun string(@StringRes resId: Int, vararg args: Any?): TextSource =
            StringResource(
                resId, args.asList()
            )

        fun number(value: Number): TextSource =
            NumberLiteral(value)

        fun string(value: String): TextSource =
            StringLiteral(value)
    }
}
