package com.boros.android.starter.shared.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.boros.android.starter.R
import com.boros.android.starter.features.main.MainActivity

object NotificationUtils {

    private val NOTIFICATION_ID = 1138

    private val POSITIVE_ACTION = "positive"
    private val NEGATIVE_ACTION = "negative"

    private val PENDING_INTENT_ID = 3417

    private val CHANNEL_ID = "notification_channel"
    private val POSITIVE_ACTION_PENDING_INTENT_ID = 1
    private val NEGATIVE_ACTION_PENDING_INTENT_ID = 14

    fun clearAllNotifications(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    fun sendAlertNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                    CHANNEL_ID,
                    context.getString(R.string.notification_channel),
                    NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(mChannel)
        }
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_content))
                .setStyle(NotificationCompat.BigTextStyle().bigText(context.getString(R.string.notification_additional_content)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(positiveAction(context))
                .addAction(negativeAction(context))
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun negativeAction(context: Context): NotificationCompat.Action {
        val ignoreReminderIntent = Intent(context, MainActivity::class.java)
        ignoreReminderIntent.action = NEGATIVE_ACTION
        val ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                NEGATIVE_ACTION_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        return NotificationCompat.Action(R.mipmap.ic_launcher,
                context.getString(R.string.no),
                ignoreReminderPendingIntent)
    }

    private fun positiveAction(context: Context): NotificationCompat.Action {
        val incrementWaterCountIntent = Intent(context, MainActivity::class.java)
        incrementWaterCountIntent.action = POSITIVE_ACTION
        val incrementWaterPendingIntent = PendingIntent.getService(
                context,
                POSITIVE_ACTION_PENDING_INTENT_ID,
                incrementWaterCountIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)
        return NotificationCompat.Action(R.mipmap.ic_launcher,
                context.getString(R.string.yes),
                incrementWaterPendingIntent)
    }

    private fun contentIntent(context: Context): PendingIntent {
        val startActivityIntent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(
                context,
                PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun largeIcon(context: Context): Bitmap {
        val res = context.resources
        return BitmapFactory.decodeResource(res, R.mipmap.ic_launcher)
    }

}