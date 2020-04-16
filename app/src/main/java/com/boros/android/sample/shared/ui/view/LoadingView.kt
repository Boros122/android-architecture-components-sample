package com.boros.android.sample.shared.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.boros.android.sample.R
import com.boros.android.sample.shared.util.extensions.disableTouch
import kotlinx.android.synthetic.main.view_loading.view.*

class LoadingView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_loading, this)
        rootFrameLayout?.disableTouch()
    }

    fun setTransparentBackground() {
        context?.let {
            rootFrameLayout?.setBackgroundColor(ContextCompat.getColor(it, R.color.colorTransparent))
        }
    }

    fun setBasicBackground() {
        context?.let {
            rootFrameLayout?.setBackgroundColor(
                    ContextCompat.getColor(it, R.color.colorLoadingContainer)
            )
        }
    }

}