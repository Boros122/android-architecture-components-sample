package com.boros.android.sample.features.main.repoList

import com.boros.android.sample.shared.ui.recyclerView.BaseCellModel

data class RepoCellModel(
        override val cellId: String,
        val repoId: Int,
        val repoName: String,
        val ownerName: String?,
        val ownerImage: String
) : BaseCellModel