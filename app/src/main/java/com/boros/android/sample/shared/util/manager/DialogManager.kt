package com.boros.android.sample.shared.util.manager

import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.boros.android.sample.shared.ui.dialog.ConfirmDialogView
import com.boros.android.sample.shared.ui.dialog.InfoDialogView
import com.boros.android.sample.shared.ui.dialog.InputDialogView
import com.boros.android.sample.shared.ui.dialog.optionSelector.OptionSelectorDialogView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DialogManager @Inject constructor() {

    private var dialog: Dialog? = null

    fun showInfoDialog(context: Context?, message: String, positiveAction: () -> Unit = {}) {
        context?.let {
            val width = (context.resources.displayMetrics.widthPixels * 0.85).toInt()
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)

            val infoDialog = InfoDialogView(context)
            dialog?.setContentView(infoDialog)

            val lp = dialog?.window?.attributes
            dialog?.window?.setLayout(width, lp?.height ?: 0)

            dialog?.setOnDismissListener {}

            infoDialog.bind(message, positiveAction, { dialog?.dismiss() })

            (context as? Activity)?.let {
                if (!it.isFinishing) {
                    dialog?.show()
                }
            }
        }
    }

    fun showConfirmDialog(context: Context?, title: String, subTitle: String? = null, positiveAction: () -> Unit = {}, negativeAction: () -> Unit = {}) {
        context?.let {
            val width = (context.resources.displayMetrics.widthPixels * 0.85).toInt()
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)

            val confirmDialog = ConfirmDialogView(context)
            dialog?.setContentView(confirmDialog)

            val lp = dialog?.window?.attributes
            dialog?.window?.setLayout(width, lp?.height ?: 0)

            dialog?.setOnDismissListener {}

            confirmDialog.bind(title, subTitle, positiveAction, negativeAction, { dialog?.dismiss() })

            (context as? Activity)?.let {
                if (!it.isFinishing) {
                    dialog?.show()
                }
            }
        }
    }

    fun showInputDialog(context: Context?, model: InputDialogView.Companion.InputDialogModel) {
        context?.let {
            val width = (context.resources.displayMetrics.widthPixels * 0.85).toInt()
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)

            val inputDialog = InputDialogView(context)
            dialog?.setContentView(inputDialog)

            val lp = dialog?.window?.attributes
            dialog?.window?.setLayout(width, lp?.height ?: 0)

            dialog?.setOnDismissListener {}

            inputDialog.bind(model) { dialog?.dismiss() }

            (context as? Activity)?.let {
                if (!it.isFinishing) {
                    dialog?.show()
                }
            }
        }
    }

    fun showOptionSelectorDialog(context: Context?, model: OptionSelectorDialogView.Companion.OptionSelectorDialogModel) {
        context?.let {
            val width = (context.resources.displayMetrics.widthPixels * 0.85).toInt()
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)

            val optionSelectorDialog = OptionSelectorDialogView(context)
            dialog?.setContentView(optionSelectorDialog)

            val lp = dialog?.window?.attributes
            dialog?.window?.setLayout(width, lp?.height ?: 0)

            dialog?.setOnDismissListener {}

            optionSelectorDialog.bind(model) { dialog?.dismiss() }

            (context as? Activity)?.let {
                if (!it.isFinishing) {
                    dialog?.show()
                }
            }
        }
    }

}