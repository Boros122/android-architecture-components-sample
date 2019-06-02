package com.boros.android.starter.ui.view.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.starter.R
import com.boros.android.starter.ui.view.BaseView
import com.boros.android.starter.util.extensions.gone
import com.boros.android.starter.util.extensions.visible
import kotlinx.android.synthetic.main.view_input_dialog.view.*

class InputDialogView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.view_input_dialog, this)
    }

    fun bind(inputDialogModel: InputDialogModel, dismiss: () -> Unit = {}) {
        titleTextView?.text = inputDialogModel.title

        if (inputDialogModel.subTitle != null) {
            subTitleTextView?.visible()
            subTitleTextView?.text = inputDialogModel.subTitle
        } else {
            subTitleTextView?.gone()
        }

        if (inputDialogModel.hint != null) {
            inputContainerInputLayout?.hint = inputDialogModel.hint
        } else {
            inputContainerInputLayout?.hint = ""
        }

        okTextView?.setOnClickListener {
            val inputText = getAddedInputText()
            if (inputText.isNotEmpty()) {
                inputContainerInputLayout.error = ""
                inputDialogModel.positiveAction(getAddedInputText())
                dismiss()
            } else {
                inputContainerInputLayout.error = resources.getString(R.string.not_valid_input)
            }
        }

        discardTextView?.setOnClickListener {
            inputDialogModel.negativeAction()
            dismiss()
        }
    }

    private fun getAddedInputText() = inputEditText?.text?.toString() ?: ""

    companion object {
        data class InputDialogModel(
                val title: String,
                val subTitle: String? = null,
                val hint: String? = null,
                val positiveAction: (addedInput: String) -> Unit = {},
                val negativeAction: () -> Unit = {}
        )
    }

}