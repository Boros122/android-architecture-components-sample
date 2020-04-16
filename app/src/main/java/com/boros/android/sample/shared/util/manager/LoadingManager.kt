package com.boros.android.sample.shared.util.manager

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.boros.android.sample.shared.ui.view.LoadingView
import com.boros.android.sample.shared.util.extensions.fadeOut
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingManager @Inject constructor() {

    private val fadeOutAnimTime = 300L

    private val loadingViewTag = "LOADING_VIEW_TAG"

    // Start loading on top of whole activity
    fun startLoading(context: Context?) {
        startLoading(context = context, appearance = LoadingAppearance.NOT_TRANSPARENT)
    }

    // Start loading with transparent background on top of whole activity
    fun startTransparentLoading(context: Context?) {
        startLoading(context = context, appearance = LoadingAppearance.TRANSPARENT)
    }

    // End loading on top of the whole activity
    fun endLoading(context: Context?) {
        removeLoading(context)
    }

    private fun startLoading(
            context: Context?,
            appearance: LoadingAppearance = LoadingAppearance.NOT_TRANSPARENT
    ) {
        context ?: return
        if (context is Activity) {
            val rootView = findRootView(context)
            rootView ?: return
            if (findLoadingViewByTag(rootView) != null) {
                return
            }
            val loadingView = createLoadingView(context)
            setLoadingViewStyle(loadingView, appearance)
            rootView.addView(loadingView)
        }
    }

    private fun removeLoading(context: Context?) {
        context ?: return
        if (context is Activity) {
            val rootView = findRootView(context)
            findLoadingViewByTag(rootView)?.let { loadingView ->
                if (loadingView.visibility == View.VISIBLE) {
                    loadingView.clearAnimation()
                    loadingView.fadeOut(duration = fadeOutAnimTime) {
                        rootView?.removeView(loadingView)
                    }
                }
            }
        }
    }

    private fun findRootView(context: Context?): ViewGroup? {
        if (context is Activity) {
            return context.window.decorView.findViewById(android.R.id.content)
        }
        return null
    }

    private fun createLoadingView(context: Context): LoadingView {
        val loadingView = LoadingView(context)
        loadingView.tag = loadingViewTag
        loadingView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        )
        return loadingView
    }

    private fun setLoadingViewStyle(
            loadingView: LoadingView?,
            appearance: LoadingAppearance
    ) {
        when (appearance) {
            LoadingAppearance.TRANSPARENT -> {
                loadingView?.setTransparentBackground()
            }
            LoadingAppearance.NOT_TRANSPARENT -> {
                loadingView?.setBasicBackground()
            }
        }
    }

    private fun findLoadingViewByTag(rootView: ViewGroup?): LoadingView? {
        return rootView?.findViewWithTag(loadingViewTag)
    }

    private enum class LoadingAppearance {
        TRANSPARENT,
        NOT_TRANSPARENT
    }

}