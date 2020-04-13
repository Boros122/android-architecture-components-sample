package com.boros.android.starter.shared.util.manager

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

// TODO refactor
object KeyboardManager {

    fun hideSoftKeyboard(context: Context?) {
        context ?: return
        val currentFocus = (context as Activity).currentFocus
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }

}