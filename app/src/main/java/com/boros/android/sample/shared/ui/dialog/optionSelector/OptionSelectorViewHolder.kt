package com.boros.android.sample.shared.ui.dialog.optionSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_option_selector_dialog_item.view.*

class OptionSelectorViewHolder(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemModel: OptionSelectorDialogView.Companion.ItemModel, listener: OptionSelectorDialogView.Companion.OptionSelectedListener, dismiss: () -> Unit) {
        itemView.labelTextView?.text = itemModel.title
        itemView.setOnClickListener {
            listener.onOptionSelected(itemModel.id)
            dismiss()
        }
    }

}