package com.dvipersquad.gallery.coreUI.mvvm.actions

import com.dvipersquad.gallery.coreUI.TextSource

sealed class CommonViewEvent : ViewAction {
    object GoBack : CommonViewEvent()

    data class ShowErrorSnackBar(
        val message: TextSource,
        val actionMessage: TextSource = TextSource.empty(),
        val action: (() -> Unit)? = null
    ) : CommonViewEvent() {
        companion object {
            operator fun invoke(
                message: String,
                actionMessage: String = "",
                action: (() -> Unit)? = null
            ): ShowErrorSnackBar {
                return ShowErrorSnackBar(
                    TextSource.string(message),
                    TextSource.string(actionMessage),
                    action
                )
            }
        }
    }

    object HideKeyboard : CommonViewEvent()
}
