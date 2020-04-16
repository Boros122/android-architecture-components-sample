package com.boros.android.sample.shared.ui.dialog.optionSelector

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boros.android.sample.R
import com.boros.android.sample.shared.ui.recyclerView.RecyclerViewAdapterBase

class OptionSelectorDialogRecyclerViewAdapter(
        itemModels: ArrayList<OptionSelectorDialogView.Companion.ItemModel>,
        private val dismiss: () -> Unit,
        private val listener: OptionSelectorDialogView.Companion.OptionSelectedListener
) : RecyclerViewAdapterBase<OptionSelectorDialogView.Companion.ItemModel, OptionSelectorViewHolder>() {

    init {
        items = itemModels
    }

    override fun onBindViewHolder(holder: OptionSelectorViewHolder, position: Int) {
        holder.bind(items[position], listener, dismiss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionSelectorViewHolder {
        val itemView = LayoutInflater
                .from(parent.context).inflate(R.layout.view_option_selector_dialog_item, parent, false)
        return OptionSelectorViewHolder(itemView)
    }

}