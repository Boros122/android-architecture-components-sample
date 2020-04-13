package com.boros.android.starter.shared.util

import android.os.CountDownTimer

// TODO refactor
object CountdownUtil {

    fun countdown(interval: Long, period: Long, periodicAction: () -> Unit, finishAction: () -> Unit) {
        object : CountDownTimer(period, interval) {
            override fun onTick(millisUntilFinished: Long) {
                periodicAction()
            }

            override fun onFinish() {
                finishAction()
            }
        }.start()
    }

}