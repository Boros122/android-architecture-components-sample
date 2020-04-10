package com.boros.android.starter.shared.ui.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.starter.R
import com.boros.android.starter.shared.ui.view.BaseView
import com.boros.android.starter.shared.util.extensions.gone
import com.boros.android.starter.shared.util.extensions.visible
import kotlinx.android.synthetic.main.view_confirm_dialog.view.*

class ConfirmDialogView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.view_confirm_dialog, this)
    }

    fun bind(text: String, subTitleText: String? = null, positiveAction: () -> Unit = {}, negativeAction: () -> Unit = {}, dismiss: () -> Unit = {}) {
        titleTextView?.text = text

        if (subTitleText != null) {
            subTitleTextView.visible()
            subTitleTextView.text = subTitleText
        } else {
            subTitleTextView.gone()
        }

        okTextView?.setOnClickListener {
            positiveAction()
            dismiss()
        }

        discardTextView?.setOnClickListener {
            negativeAction()
            dismiss()
        }
    }

}