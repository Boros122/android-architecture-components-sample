package com.boros.android.starter.ui.view.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.starter.R
import com.boros.android.starter.ui.view.BaseView
import kotlinx.android.synthetic.main.view_option_selector_dialog_item.view.*

class OptionSelectorItemView @JvmOverloads constructor(context: Context?, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_option_selector_dialog_item, this)
    }

    fun bind(itemModel: OptionSelectorDialogView.Companion.ItemModel, listener: OptionSelectorDialogView.Companion.OptionSelectedListener, dismiss: () -> Unit) {
        labelTextView?.text = itemModel.title
        setOnClickListener {
            listener.onOptionSelected(itemModel.id)
            dismiss()
        }
    }

}