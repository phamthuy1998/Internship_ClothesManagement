package com.n16dccn159.admin.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import coil.ImageLoader
import coil.request.GetRequest
import coil.request.SuccessResult
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.n16dccn159.admin.R
import com.readystatesoftware.chuck.internal.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("annaToken", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("Tag", "Recive message=${remoteMessage.notification?.title}")

//        if (getBooleanPref(Constant.IS_CUSTOMER))
        sendNotification(remoteMessage)
    }

    private suspend fun loadBitmapImage(url: String?): Bitmap? {
        val loader = ImageLoader(this)
        val request = GetRequest.Builder(this)
            .data(url)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val result = (loader.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    private fun sendNotification(remoteMessage: RemoteMessage?) {

        val intent = Intent(this, MainActivity::class.java)
            .putExtra("id_product", remoteMessage?.data?.get("id_product"))
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val stackBuilder = TaskStackBuilder.create(applicationContext)
        stackBuilder.addNextIntentWithParentStack(intent)

        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)
        val chaneId = "test_chanel_id"
        val defaultSoundUri = RingtoneManager
            .getActualDefaultRingtoneUri(
                this,
                RingtoneManager.TYPE_NOTIFICATION
            )

        var bitmap: Bitmap? = null
        runBlocking {
            bitmap =
                withContext(Dispatchers.Default) { loadBitmapImage(remoteMessage?.data?.get("image")) }
        }

        val notificationBuilder = NotificationCompat.Builder(this, chaneId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(remoteMessage?.data?.get("title"))
            .setContentText(remoteMessage?.data?.get("name"))
            .setAutoCancel(true)
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
            )
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chane = NotificationChannel(
                chaneId,
                "MyTiki",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(chane)
            notificationBuilder.setChannelId(chaneId)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

}