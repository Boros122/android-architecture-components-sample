package com.boros.android.starter.adapter

import android.view.View
import android.view.ViewGroup
import com.boros.android.starter.itemModel.BaseItemModel
import com.boros.android.starter.itemModel.RepoItemModel
import com.boros.android.starter.ui.view.RepoCellView
import com.boros.android.starter.util.ViewWrapper

class RepoListRecyclerViewAdapter : RecyclerViewAdapterBase<BaseItemModel, View>() {

    private val ITEM_TYPE = 0

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View {
        return when (viewType) {
            ITEM_TYPE -> RepoCellView(parent.context)
            else -> View(parent.context)
        }
    }

    override fun onBindViewHolder(holder: ViewWrapper<View>, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE && holder.view is RepoCellView) {
            holder.view.bind(items[position] as RepoItemModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is RepoItemModel -> return ITEM_TYPE
            else -> -1
        }
    }

}