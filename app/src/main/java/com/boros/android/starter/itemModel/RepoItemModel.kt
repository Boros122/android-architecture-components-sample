package com.boros.android.starter.itemModel

data class RepoItemModel(
        val repoId: Int,
        val repoName: String,
        val ownerName: String?,
        val ownerImage: String
) : BaseItemModel