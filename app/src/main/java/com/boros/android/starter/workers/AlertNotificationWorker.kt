package com.boros.android.starter.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.boros.android.starter.notification.NotificationUtils

class AlertNotificationWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        NotificationUtils.sendAlertNotification(applicationContext)
        return Result.success()
    }

}