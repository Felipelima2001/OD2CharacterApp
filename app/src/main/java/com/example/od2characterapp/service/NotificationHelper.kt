package com.example.od2characterapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.od2characterapp.R
import com.example.od2characterapp.data.local.entity.PersonagemEntity

object NotificationHelper {

    const val CHANNEL_ID = "battle_channel"

    fun createChannel(context: Context) {

        // Channels exist only API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Batalha",
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }
    fun buildForegroundNotification(context: Context) =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Batalha em andamento")
            .setContentText("Seu personagem está lutando...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

    fun showDeathNotification(context: Context, personagem: PersonagemEntity) {

        // POST_NOTIFICATIONS – necessário apenas API 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = context.checkSelfPermission(
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) return
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Seu personagem morreu")
            .setContentText("${personagem.nome} foi derrotado.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(context).notify(999, notification)
    }
}
