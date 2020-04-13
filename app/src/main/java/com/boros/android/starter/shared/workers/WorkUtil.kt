package com.boros.android.starter.shared.workers

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkUtil {

    private val workManager = WorkManager.getInstance()

    private var alertWorker: OneTimeWorkRequest? = null

    fun scheduleAlertNotificationWork() {
        if (alertWorker == null) {
            alertWorker = OneTimeWorkRequest.Builder(AlertNotificationWorker::class.java).setInitialDelay(15, TimeUnit.MINUTES).build()
        }
        alertWorker?.let {
            workManager.cancelWorkById(it.id)
            workManager.enqueue(it)
        }
    }

    fun cancelAlertNotificationWorker() {
        alertWorker?.let {
            workManager.cancelWorkById(it.id)
        }
    }

}