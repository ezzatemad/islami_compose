package com.example.islamicompose.radio

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import javax.inject.Inject

class RadioBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when (intent?.action) {
                RadioNotification.ACTION_STOP -> {
                    val stopIntent = Intent(context, RadioNotification::class.java)
                    stopIntent.action = RadioNotification.ACTION_STOP
                    context?.startService(stopIntent)
                }
                RadioNotification.ACTION_PLAY -> {
                    val playIntent = Intent(context, RadioNotification::class.java)
                    playIntent.action = RadioNotification.ACTION_PLAY
                    context?.startService(playIntent)
                }
            }
        }
    }
}