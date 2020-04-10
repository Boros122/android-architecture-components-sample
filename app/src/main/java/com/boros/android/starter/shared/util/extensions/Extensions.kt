package com.boros.android.starter.shared.util.extensions

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.*

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T?>) {
    removeObserver(observer)
    observe(owner, observer)
}

//Generates a random number from a given range: (1..100).random()
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) + start

fun Context?.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()