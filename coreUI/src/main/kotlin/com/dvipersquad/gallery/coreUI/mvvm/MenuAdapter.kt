package com.dvipersquad.gallery.coreUI.mvvm

interface MenuAdapter {

    fun setOptionClickListener(optionClickListener: OptionClickListener)

    interface OptionClickListener {
        fun onItemOptionClick(option: MenuOption) {}
    }

    open class MenuOption
}
