package com.boros.android.starter.shared.ui.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListSpacesItemDecorator(private val space: Int, private val needTopSpaceForFirstItem: Boolean = true) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.bottom = space

        // Add top margin only for the first item to avoid double space between items
        if ((parent.getChildLayoutPosition(view) == 0) && needTopSpaceForFirstItem) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }

}