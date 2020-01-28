package com.dvipersquad.gallery.feature.image.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.dvipersquad.gallery.coreUI.mvvm.MvvmFragment
import com.dvipersquad.gallery.feature.image.details.databinding.ImageDetailsFragmentBinding
import com.veinhorn.scrollgalleryview.ScrollGalleryView
import com.veinhorn.scrollgalleryview.builder.GallerySettings
import ogbe.ozioma.com.glideimageloader.dsl.DSL.images
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class ImageDetailsFragment :
    MvvmFragment<ImageDetailsFragmentBinding, ImageDetailsViewModel>(ImageDetailsViewModel::class) {

    private var isToolBarShown = true
    private lateinit var scrollGalleryView: ScrollGalleryView

    override fun getParameters(): ParametersDefinition = {
        val args = arguments ?: throw IllegalArgumentException("No arguments provided")
        val imageDetailsArgs = ImageDetailsFragmentArgs.fromBundle(args)
        parametersOf(imageDetailsArgs.imageDetailsInput)
    }

    override fun getLayoutId() = R.layout.image_details_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.imageDetailsToolbar.apply {
            setupWithNavController(findNavController())
        }

        scrollGalleryView = ScrollGalleryView
            .from(view.findViewById(R.id.scrollGallery))
            .settings(
                GallerySettings
                    .from(childFragmentManager)
                    .thumbnailSize(100)
                    .enableZoom(true)
                    .build()
            )
            .onPageChangeListener(ViewPager.SimpleOnPageChangeListener())
            .onImageClickListener {
                toggleSystemUI()
            }
            .add(images(actionViewModel.imageDetailsInput.images))
            .build()
        scrollGalleryView.hideThumbnailsOnClick(true)
        val thumbnails =
            scrollGalleryView.findViewById<View>(com.veinhorn.scrollgalleryview.R.id.thumbnails_container)
        thumbnails.layoutParams = (thumbnails.layoutParams as FrameLayout.LayoutParams).apply {
            bottomMargin = resources.getDimension(R.dimen.thumbnails_margin)
                .toInt()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageDetailsToolbar.setupWithNavController(findNavController())
    }

    private fun toggleSystemUI() {
        if (isToolBarShown) {
            hideSystemUI()
        } else {
            showSystemUI()
        }
    }

    private fun hideSystemUI() {
        isToolBarShown = false
        binding.imageDetailsToolbar.animate()
            .translationY(-binding.imageDetailsToolbar.height.toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.imageDetailsToolbar.visibility = View.GONE
                    binding.imageDetailsToolbar.animate()
                        .setListener(null)
                }
            })
        requireActivity().window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        isToolBarShown = true
        binding.imageDetailsToolbar.visibility = View.VISIBLE
        binding.imageDetailsToolbar.animate()
            .translationY(0f)
        requireActivity().window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_VISIBLE)
    }

    override fun onDestroyView() {
        scrollGalleryView.viewPager.clearOnPageChangeListeners()
        scrollGalleryView.addOnImageClickListener(null)
        showSystemUI()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onDestroyView()
    }
}
