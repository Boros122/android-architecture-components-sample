package com.boros.android.starter.shared.ui.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomSpaceItemDecoaration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (state.itemCount - 1 == parent.getChildLayoutPosition(view)) {
            outRect.bottom = space
        }
    }

}