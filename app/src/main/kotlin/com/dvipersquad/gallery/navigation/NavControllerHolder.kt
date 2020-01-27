package com.dvipersquad.gallery.navigation

import androidx.navigation.NavController
import java.lang.ref.WeakReference

interface NavControllerHolder {
    fun get(): NavController

    fun setNavController(navController: NavController)

    fun clearNavController()
}

class RootNavControllerHolder : NavControllerHolder {
    private var navController: WeakReference<NavController>? = null

    override fun get(): NavController {
        return navController?.get()!!
    }

    override fun setNavController(navController: NavController) {
        this.navController = WeakReference(navController)
    }

    override fun clearNavController() {
        this.navController = null
    }
}
