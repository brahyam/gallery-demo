package com.dvipersquad.gallery.coreUI.mvvm.actions

import androidx.annotation.StringRes

const val KEY_SHARED_MESSAGE_DIALOG_VIEW_MODEL = "sharedDialogViewModel"

sealed class DialogViewAction : ViewAction

data class ShowErrorDialogViewAction(val message: String) : DialogViewAction()

data class ShowResMessageDialogViewAction(
    @StringRes val titleResource: Int,
    @StringRes val messageResource: Int,
    val positiveClickListener: () -> Unit = {}
) : DialogViewAction()

data class ShowStringMessageDialogViewAction(
    val title: String,
    val message: String,
    val positiveClickListener: () -> Unit = {}
) : DialogViewAction()

object ShowVersionDeprecatedDialogAction : DialogViewAction()

object ShowUserBlockedDialogViewAction : DialogViewAction()

data class ShowSnackBarViewAction(
    val message: String,
    val actionMessage: String = "",
    val action: () -> Unit
) : DialogViewAction()
