package com.boros.android.sample.features.main.repoList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.boros.android.sample.shared.ui.recyclerView.OnItemClickListener
import com.boros.android.sample.shared.util.GlideUtil
import com.boros.android.sample.shared.util.extensions.gone
import com.boros.android.sample.shared.util.extensions.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_repo_cell.view.*

class RepoCellViewHolder(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(cellModel: RepoCellModel, clickListener: OnItemClickListener?) {
        Glide.with(itemView.context)
                .load(cellModel.ownerImage)
                .apply(GlideUtil.circleRequestOption())
                .into(itemView.ownerImageView)

        itemView.repoNameTextView?.text = cellModel.repoName

        if (cellModel.ownerName != null) {
            itemView.ownerNameTextView?.visible()
            itemView.ownerNameTextView?.text = cellModel.ownerName
        } else {
            itemView.ownerNameTextView?.gone()
        }

        itemView.rootConstraintLayout?.setOnClickListener {
            clickListener?.onItemClicked(cellModel.repoId.toString())
        }
    }

}