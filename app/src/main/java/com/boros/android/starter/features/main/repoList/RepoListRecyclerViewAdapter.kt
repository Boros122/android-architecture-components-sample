package com.boros.android.starter.features.main.repoList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boros.android.starter.R
import com.boros.android.starter.shared.ui.recyclerView.RecyclerViewAdapterBase

class RepoListRecyclerViewAdapter : RecyclerViewAdapterBase<RepoCellModel, RepoCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCellViewHolder {
        val itemView = LayoutInflater
                .from(parent.context).inflate(R.layout.view_repo_cell, parent, false)
        return RepoCellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoCellViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

}