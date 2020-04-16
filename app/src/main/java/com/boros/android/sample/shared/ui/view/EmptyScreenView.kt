package com.boros.android.sample.shared.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.sample.R
import kotlinx.android.synthetic.main.view_empty_screen.view.*

class EmptyScreenView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defaultStyle: Int = 0
) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_empty_screen, this)
    }

    fun setEmptyText(text: String) {
        emptyText?.text = text
    }

}