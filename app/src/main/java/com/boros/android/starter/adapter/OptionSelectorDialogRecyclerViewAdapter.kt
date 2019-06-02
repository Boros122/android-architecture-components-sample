package com.boros.android.starter.adapter

import android.view.ViewGroup
import com.boros.android.starter.ui.view.dialog.OptionSelectorDialogView
import com.boros.android.starter.ui.view.dialog.OptionSelectorItemView
import com.boros.android.starter.util.ViewWrapper

class OptionSelectorDialogRecyclerViewAdapter(
        itemModels: ArrayList<OptionSelectorDialogView.Companion.ItemModel>,
        private val dismiss: () -> Unit,
        private val listener: OptionSelectorDialogView.Companion.OptionSelectedListener
) : RecyclerViewAdapterBase<OptionSelectorDialogView.Companion.ItemModel, OptionSelectorItemView>() {

    init {
        items = itemModels
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): OptionSelectorItemView {
        return OptionSelectorItemView(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<OptionSelectorItemView>, position: Int) {
        holder.view.bind(items[position], listener, dismiss)
    }

}