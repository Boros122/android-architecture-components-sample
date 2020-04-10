package com.boros.android.starter.shared.ui.dialog.optionSelector

import android.view.ViewGroup
import com.boros.android.starter.shared.ui.recyclerView.RecyclerViewAdapterBase
import com.boros.android.starter.shared.ui.recyclerView.ViewWrapper

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