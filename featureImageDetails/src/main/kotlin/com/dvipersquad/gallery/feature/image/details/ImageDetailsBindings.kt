package com.dvipersquad.gallery.feature.image.details

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.viewpager.widget.ViewPager
import com.veinhorn.scrollgalleryview.ScrollGalleryView

@BindingAdapter("currentItem")
fun setCurrentItem(galleryView: ScrollGalleryView, position: Int) {
    galleryView.currentItem = position
}

@InverseBindingAdapter(attribute = "currentItem")
fun getCurrentItem(galleryView: ScrollGalleryView) = galleryView.currentItem

@BindingAdapter("currentItemAttrChanged")
fun setListener(galleryView: ScrollGalleryView, listener: InverseBindingListener) {
    galleryView.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            listener.onChange()
        }
    })
}
