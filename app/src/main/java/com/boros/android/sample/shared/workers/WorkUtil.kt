package com.boros.android.sample.shared.workers

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkUtil @Inject constructor(context: Context) {

    private val workManager = WorkManager.getInstance(context)

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