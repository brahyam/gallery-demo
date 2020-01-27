package com.dvipersquad.gallery

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.dvipersquad.gallery.coreUI.mvvm.actions.CommonViewEvent
import com.dvipersquad.gallery.coreUI.mvvm.actions.SharedActionViewModel
import com.dvipersquad.gallery.coreUI.mvvm.actions.ShowStringMessageDialogViewAction
import com.dvipersquad.gallery.coreUI.setBackgroundColor
import com.dvipersquad.gallery.coreUI.setMessageTextColor
import com.dvipersquad.gallery.navigation.NavControllerHolder
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val sharedActionViewModel by viewModel(SharedActionViewModel::class)
    private val navControllerHolder: NavControllerHolder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Gallery)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navControllerHolder.setNavController(findNavController(R.id.nav_host))
        sharedActionViewModel.observeAction(this) { action ->
            when (action) {
                is ShowStringMessageDialogViewAction -> {
                    createMessageDialog(
                        this,
                        action.title,
                        action.message,
                        action.positiveClickListener
                    ).show()
                }
                is CommonViewEvent.ShowErrorSnackBar -> {
                    showErrorSnackBar(
                        action.message.retrieveString(resources),
                        action.actionMessage.retrieveString(resources),
                        action.action
                    )
                }
            }
        }
    }

    private fun showErrorSnackBar(message: String, actionMessage: String, action: (() -> Unit)?) {
        val view: CoordinatorLayout =
            findViewById(R.id.root_coordinator) ?: throw IllegalStateException(
                "CoordinatorLayout with id root_coordinator is missing"
            )

        val duration =
            if (action == null || actionMessage.isBlank()) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE

        Snackbar.make(view, message, duration)
            .setActionTextColor(ContextCompat.getColor(baseContext, R.color.error_snack_bar_action))
            .setMessageTextColor(ContextCompat.getColor(baseContext, R.color.error_snack_bar_text))
            .setBackgroundColor(
                ContextCompat.getColor(
                    baseContext,
                    R.color.error_snack_bar_background
                )
            )
            .setAction(actionMessage) {
                Timber.d("Action $action clicked")
                action?.invoke()
            }
            .show()
    }

    private fun createMessageDialog(
        context: Context,
        title: String,
        message: String,
        positiveClickListener: (() -> Unit)? = null
    ): Dialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
                positiveClickListener?.invoke()
            }
            .create()
    }

    override fun onDestroy() {
        navControllerHolder.clearNavController()
        super.onDestroy()
    }
}
