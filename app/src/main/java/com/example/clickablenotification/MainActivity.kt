package com.example.clickablenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            nm.createNotificationChannel(NotificationChannel("first", "default", NotificationManager.IMPORTANCE_DEFAULT))
        }

        button1.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this,123,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setContentIntent(pi)
                .setContentTitle("Clickable Notification")
                .setContentText("This is sample description of the Notification")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,clickableNotification)
        }

        button2.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this, 123 , intent , PendingIntent.FLAG_UPDATE_CURRENT )

            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setContentIntent(pi)
                .setContentTitle("Clickable Notification")
                .addAction(R.drawable.ic_launcher_foreground, "Click Me" , pi)
                .setContentTitle("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(2,clickableNotification)
        }
    }
}