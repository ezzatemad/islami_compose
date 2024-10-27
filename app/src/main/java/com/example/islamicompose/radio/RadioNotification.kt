package com.example.islamicompose.radio

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi

import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.islamicompose.R

@RequiresApi(Build.VERSION_CODES.Q)
class RadioNotification() : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val CHANNEL_ID = "RadioNotificationChannel"
    private val NOTIFICATION_ID = 1
    private var isPlaying = false

    companion object {
        const val ACTION_PLAY = "PLAY"
        const val ACTION_STOP = "STOP"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> {
                val url = intent.getStringExtra("RADIO_URL")
                val name = intent.getStringExtra("RADIO_TITLE")
                if (url != null && name != null) {
                    playRadio(name, url)
                }
            }

            ACTION_STOP -> stopRadio()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    @SuppressLint("ForegroundServiceType")
    private fun playRadio(radioTitle: String, url: String) {
        if (isPlaying) return

        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            setOnPreparedListener { start() }
            setOnCompletionListener { stopRadio() }
            prepareAsync()
        }

        isPlaying = true
        startForeground(NOTIFICATION_ID, createNotification(radioTitle, isPlaying = true))
    }

    private fun stopRadio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.release()
            }
        }
        mediaPlayer = null
        isPlaying = false

        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(radioTitle: String, isPlaying: Boolean): Notification {
        val playIntent = Intent(this, RadioNotification::class.java).apply { action = ACTION_PLAY }
        val stopIntent = Intent(this, RadioNotification::class.java).apply { action = ACTION_STOP }

        val playPendingIntent = PendingIntent.getService(
            this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val stopPendingIntent = PendingIntent.getService(
            this, 1, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(radioTitle)
            .setContentText(if (isPlaying) "Playing" else "Stopped")
            .setSmallIcon(R.drawable.radio_bottom)
            .addAction(
                if (isPlaying) R.drawable.stop else R.drawable.iv_radio_play,
                if (isPlaying) "Stop" else "Play",
                if (isPlaying) stopPendingIntent else playPendingIntent
            )
            .setOngoing(isPlaying)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Radio Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}  