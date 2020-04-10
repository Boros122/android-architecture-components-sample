package com.boros.android.starter.shared.ui.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.starter.R
import com.boros.android.starter.shared.ui.view.BaseView
import kotlinx.android.synthetic.main.view_info_dialog.view.*

class InfoDialogView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.view_info_dialog, this)
    }

    fun bind(text: String, positiveAction: () -> Unit = {}, dismiss: () -> Unit = {}) {
        titleTextView?.text = text

        okTextView?.setOnClickListener {
            positiveAction()
            dismiss()
        }
    }

}