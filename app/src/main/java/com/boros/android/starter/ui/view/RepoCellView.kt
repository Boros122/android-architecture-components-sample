package com.boros.android.starter.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.starter.R
import com.boros.android.starter.event.RepoListItemSelectedEvent
import com.boros.android.starter.itemModel.RepoItemModel
import com.boros.android.starter.util.GlideUtil
import com.boros.android.starter.util.extensions.fitWidthToScreen
import com.boros.android.starter.util.extensions.gone
import com.boros.android.starter.util.extensions.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_repo_cell.view.*
import org.greenrobot.eventbus.EventBus

class RepoCellView @JvmOverloads constructor(context: Context?, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_repo_cell, this)
        rootConstraintLayout?.fitWidthToScreen(context)
    }

    fun bind(itemModel: RepoItemModel) {
        Glide.with(this)
                .load(itemModel.ownerImage)
                .apply(GlideUtil.circleRequestOption())
                .into(ownerImageView)

        repoNameTextView?.text = itemModel.repoName

        if (itemModel.ownerName != null) {
            ownerNameTextView?.visible()
            ownerNameTextView?.text = itemModel.ownerName
        } else {
            ownerNameTextView?.gone()
        }

        rootConstraintLayout?.setOnClickListener { EventBus.getDefault().post(RepoListItemSelectedEvent(itemModel.repoId)) }
    }

}