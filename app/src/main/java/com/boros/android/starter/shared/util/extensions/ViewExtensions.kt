package com.boros.android.starter.shared.util.extensions

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.view.View.*
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import kotlin.math.absoluteValue

const val ANIMATION_DURATION = 300L

fun View?.visible() {
    this?.visibility = VISIBLE
}

fun View?.invisible() {
    this?.visibility = INVISIBLE
}

fun View?.gone() {
    this?.visibility = GONE
}

fun View?.enable() {
    this?.isEnabled = true
}

fun View?.disable() {
    this?.isEnabled = false
}

fun View?.measure(): View {
    this?.measure(UNSPECIFIED, UNSPECIFIED)
    return this!!
}

/**
 * Works only on focusable views
 */
fun View?.showSoftKeyboard() {
    this?.requestFocus()
    val imm = this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View?.hideSoftKeyboard() {
    this?.context?.let { context ->
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE)
        imm?.let {
            (imm as InputMethodManager).hideSoftInputFromWindow((context as? Activity)?.currentFocus?.windowToken, 0)
        }
    }
}

fun View?.scaleUp(duration: Long = ANIMATION_DURATION) {
    if (this?.scaleX != 1F || this.scaleY != 1F) {
        val scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", 0F, 1F)
        scaleUpX.duration = duration
        scaleUpX.interpolator = AccelerateDecelerateInterpolator()
        val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 0F, 1F)
        scaleUpY.duration = duration
        scaleUpY.interpolator = AccelerateDecelerateInterpolator()
        val locationAnimatorSet = AnimatorSet()
        locationAnimatorSet.play(scaleUpX)
        locationAnimatorSet.play(scaleUpY)
        locationAnimatorSet.start()
    }
}

fun View?.scaleDown(duration: Long = ANIMATION_DURATION) {
    if (this?.scaleX != 0F || this.scaleY != 0F) {
        val scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", 1F, 0F)
        scaleDownX.duration = duration
        scaleDownX.interpolator = AccelerateDecelerateInterpolator()
        val scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", 1F, 0F)
        scaleDownY.duration = duration
        scaleDownY.interpolator = AccelerateDecelerateInterpolator()
        val locationAnimatorSet = AnimatorSet()
        locationAnimatorSet.play(scaleDownX)
        locationAnimatorSet.play(scaleDownY)
        locationAnimatorSet.start()
    }
}

fun View?.fadeIn(duration: Long = ANIMATION_DURATION) {
    val fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0F, 1F)
    fadeIn.duration = duration
    fadeIn.interpolator = AccelerateDecelerateInterpolator()
    fadeIn.start()
}

fun View?.fadeOut(duration: Long = ANIMATION_DURATION) {
    val fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1F, 0F)
    fadeOut.duration = duration
    fadeOut.interpolator = AccelerateDecelerateInterpolator()
    fadeOut.start()
}

fun View?.fadeOut(duration: Long = ANIMATION_DURATION, completion: () -> Unit) {
    val fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1F, 0F)
    fadeOut.duration = duration
    fadeOut.interpolator = AccelerateDecelerateInterpolator()
    fadeOut.start()
    fadeOut.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            completion()
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })
}

fun View?.animateHeight(fromSize: Int, toSize: Int, endAnimationAction: (() -> Unit)? = null, duration: Long = ANIMATION_DURATION) {
    val animator = ValueAnimator.ofInt(fromSize, toSize).setDuration(duration)
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.addUpdateListener { animation ->
        this?.layoutParams?.height = animation.animatedValue as Int
        this?.requestLayout()
        if (animation.animatedValue == toSize) {
            this?.layoutParams?.height = toSize
            this?.requestLayout()
            endAnimationAction?.let { it() }
        }
    }
    animator.start()
}

@SuppressWarnings("deprecation")
fun TextView.setHtmlText(htmlText: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}

inline fun View?.onScroll(crossinline callback: (x: Int, y: Int) -> Unit) {
    var oldX = 0
    var oldY = 0
    this?.viewTreeObserver?.addOnScrollChangedListener {
        if (oldX != scrollX || oldY != scrollY) {
            callback(scrollX, scrollY)
            oldX = scrollX
            oldY = scrollY
        }
    }
}

inline fun View?.onScroll(crossinline callback: (oldX: Int, oldY: Int, newX: Int, newY: Int, dX: Int, dY: Int) -> Unit) {
    var oldX = 0
    var oldY = 0
    this?.viewTreeObserver?.addOnScrollChangedListener {
        if (oldX != scrollX || oldY != scrollY) {
            callback(oldX, oldY, scrollX, scrollY, (oldX - scrollX).absoluteValue, (oldY - scrollY).absoluteValue)
            oldX = scrollX
            oldY = scrollY
        }
    }
}

fun View?.enableTouch() {
    this?.setOnTouchListener { v, event -> false }
}

fun View?.disableTouch() {
    this?.setOnTouchListener { v, event -> true }
}

fun View.fitWidthToScreen(context: Context?) {
    this.layoutParams.width = context?.resources?.displayMetrics?.widthPixels ?: 0
}

inline val View?.topMargin: Int get() = (this?.layoutParams as ViewGroup.MarginLayoutParams).topMargin

inline val View?.bottomMargin: Int get() = (this?.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin