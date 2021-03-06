package com.boros.android.sample.shared.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.boros.android.sample.shared.event.InitialEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseView @JvmOverloads constructor(context: Context?, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : ConstraintLayout(context, attributeSet, defaultStyle) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        EventBus.getDefault().register(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }

    @Suppress("UNUSED_PARAMETER")
    @Subscribe
    fun initialSubscribe(event: InitialEvent) {
        // Nothing to do here
    }

}
