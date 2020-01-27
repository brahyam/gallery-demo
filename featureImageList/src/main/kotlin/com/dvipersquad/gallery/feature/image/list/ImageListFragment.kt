package com.dvipersquad.gallery.feature.image.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dvipersquad.gallery.coreUI.mvvm.MvvmFragment
import com.dvipersquad.gallery.feature.image.list.databinding.ImageListFragmentBinding

class ImageListFragment :
    MvvmFragment<ImageListFragmentBinding, ImageListViewModel>(ImageListViewModel::class) {

    override fun getLayoutId() = R.layout.image_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageListToolbar.setupWithNavController(findNavController())
    }
}
