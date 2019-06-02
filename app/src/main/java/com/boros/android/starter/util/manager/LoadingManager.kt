package com.boros.android.starter.util.manager

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.boros.android.starter.R
import com.boros.android.starter.ui.activity.MainActivity
import com.boros.android.starter.ui.view.LoadingView

object LoadingManager {

    private const val LOADING_VIEW_TAG = "LOADING_VIEW_TAG"

    fun startLoading(context: Context?, loadingType: LoadingType = LoadingType.TRANSPARENT) {
        context ?: return
        addLoadingView(context, getContainer(context), loadingType)
    }

    fun startFullScreenLoading(context: Context?, loadingType: LoadingType = LoadingType.TRANSPARENT) {
        context ?: return
        addLoadingView(context, getContainer(context), loadingType)
    }

    fun endLoading(context: Context?) {
        context ?: return
        removeLoadingView(getContainer(context))
    }

    private fun addLoadingView(context: Context, container: ViewGroup, loadingType: LoadingType) {
        if (isLoadingViewAdded(container)) {
            return
        }
        val loadingView = when (loadingType) {
            LoadingType.TRANSPARENT -> {
                LoadingView(context = context, backgroundColor = R.color.colorTransparent)
            }
            LoadingType.NOT_TRANSPARENT -> {
                LoadingView(context = context, backgroundColor = R.color.colorLoadingContainer)
            }
        }
        loadingView.tag = LOADING_VIEW_TAG
        loadingView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        container.addView(loadingView)
    }

    private fun isLoadingViewAdded(container: ViewGroup) = container.findViewWithTag<LoadingView>(LOADING_VIEW_TAG) != null

    private fun removeLoadingView(container: ViewGroup) {
        val loadingView = container.findViewWithTag<LoadingView>(LOADING_VIEW_TAG)
        container.removeView(loadingView)
    }

    private fun getContainer(context: Context): ViewGroup {
        return if (context is MainActivity) {
            context.getLoadingContainer()
        } else {
            (context as Activity).window.decorView.findViewById(android.R.id.content)
        }
    }

    enum class LoadingType {
        TRANSPARENT,
        NOT_TRANSPARENT
    }

}