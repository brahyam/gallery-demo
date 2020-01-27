package com.dvipersquad.gallery.coreUI

import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import coil.api.load
import com.google.android.material.textfield.TextInputEditText

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("invisible")
fun setViewInvisible(view: View, invisible: Boolean) {
    view.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("text")
fun TextView.setText(value: TextSource?) {
    text = value?.retrieveString(resources)
}

@BindingAdapter("text")
fun TextInputEditText.setText(value: TextSource?) {
    if (value == null) return
    text = SpannableStringBuilder.valueOf(value.retrieveString(resources))
}

@BindingAdapter("src")
fun setSrc(view: ImageView, src: String?) {
    view.load(src)
}

@BindingAdapter("src", "placeholder")
fun setSrc(view: ImageView, src: String?, placeholder: Drawable?) {
    view.load(src) {
        placeholder(placeholder)
    }
}
