package com.boros.android.starter.shared.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.boros.android.starter.R
import com.boros.android.starter.shared.util.extensions.disableTouch
import kotlinx.android.synthetic.main.view_loading.view.*

class LoadingView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defaultStyle: Int = 0, backgroundColor: Int = R.color.colorTransparent) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_loading, this)
        rootFrameLayout?.disableTouch()
        rootFrameLayout?.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    }

}