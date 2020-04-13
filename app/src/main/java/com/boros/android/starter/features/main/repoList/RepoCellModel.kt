package com.boros.android.starter.features.main.repoList

import com.boros.android.starter.shared.ui.recyclerView.BaseCellModel

data class RepoCellModel(
        override val cellId: String,
        val repoId: Int,
        val repoName: String,
        val ownerName: String?,
        val ownerImage: String
) : BaseCellModel