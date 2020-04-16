package com.boros.android.sample.shared.ui.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GridSpacesItemDecoration(private val spanCount: Int, private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        var bottomPadding = space / 2
        var topPadding = space / 2
        var rightPadding = space / 2
        var leftPadding = space / 2

        when {
            parent.getChildLayoutPosition(view) % spanCount == 0 -> {
                leftPadding = space
            }
            parent.getChildLayoutPosition(view) % spanCount == spanCount - 1 -> {
                rightPadding = space
            }
        }

        if (parent.getChildLayoutPosition(view) < spanCount) {
            topPadding = space
        }

        if (parent.getChildLayoutPosition(view) >= state.itemCount - spanCount) {
            bottomPadding = space
        }

        if (parent.getChildLayoutPosition(view) == 0) {
            leftPadding = space
        }

        if (state.itemCount - 1 == parent.getChildLayoutPosition(view)) {
            rightPadding = space
        }

        (view as? ViewGroup)?.getChildAt(0)?.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
    }
}