package com.boros.android.sample.shared.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.boros.android.sample.shared.notification.NotificationUtils

class AlertNotificationWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        NotificationUtils.sendAlertNotification(applicationContext)
        return Result.success()
    }

}