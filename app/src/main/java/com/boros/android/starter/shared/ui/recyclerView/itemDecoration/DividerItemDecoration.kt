package com.boros.android.starter.shared.ui.recyclerView.itemDecoration

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import com.boros.android.starter.R

class DividerItemDecoration(context: Context?) : RecyclerView.ItemDecoration() {

    private val dividerLine = context?.getDrawable(R.drawable.background_divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (dividerLine?.intrinsicHeight ?: 0)

            dividerLine?.setBounds(left, top, right, bottom)
            dividerLine?.draw(c)
        }
    }

}