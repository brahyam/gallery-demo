package com.dvipersquad.gallery.coreUI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setBackgroundColor(@ColorInt colorInt: Int) = apply {
    view.setBackgroundColor(colorInt)
}

fun Snackbar.setMessageTextColor(@ColorInt colorInt: Int) = apply {
    (view.findViewById(R.id.snackbar_text) as TextView).setTextColor(colorInt)
}

fun FragmentActivity.hideKeyboard(flags: Int = 0) = currentFocus?.hideSoftInput(flags) ?: false

fun FragmentActivity.clearFocus() = currentFocus?.clearFocus()

fun View.hideSoftInput(flags: Int = 0) =
    context.inputMethodManager.hideSoftInputFromWindow(windowToken, flags)

fun View.showSoftInput(flags: Int = InputMethodManager.SHOW_IMPLICIT) =
    context.inputMethodManager.showSoftInput(this, flags)

private val Context.inputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes idRes: Int, attachToRoot: Boolean = true): T {
    return DataBindingUtil.inflate(layoutInflater, idRes, this, attachToRoot)
}
