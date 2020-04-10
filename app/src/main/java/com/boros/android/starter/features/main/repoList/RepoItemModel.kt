package com.boros.android.starter.features.main.repoList

import com.boros.android.starter.shared.ui.recyclerView.BaseItemModel

data class RepoItemModel(
        val repoId: Int,
        val repoName: String,
        val ownerName: String?,
        val ownerImage: String
) : BaseItemModel