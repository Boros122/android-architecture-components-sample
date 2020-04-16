package com.boros.android.sample.shared.ui.recyclerView.itemDecoration

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import com.boros.android.sample.R

class FrameItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var dividerLine = context.getDrawable(R.drawable.background_divider)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            //top line
            dividerLine?.setBounds(child.left, child.top, child.right, child.top + (dividerLine?.intrinsicHeight
                    ?: 0))
            dividerLine?.draw(canvas)

            //vertical line on the right side
            dividerLine?.setBounds(child.right - (dividerLine?.intrinsicHeight
                    ?: 0), child.top, child.right, child.bottom)
            dividerLine?.draw(canvas)

            //vertical line on the left side
            dividerLine?.setBounds(child.left, child.top, child.left + (dividerLine?.intrinsicHeight
                    ?: 0), child.bottom)
            dividerLine?.draw(canvas)

            //bottom line
            dividerLine?.setBounds(child.left, child.bottom - (dividerLine?.intrinsicHeight
                    ?: 0), child.right, child.bottom)
            dividerLine?.draw(canvas)
        }
    }

}