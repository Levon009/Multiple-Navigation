package com.example.jetpack_multiplenavigation.broadcastDynamicReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class AirPlaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isTurnOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) != 0

            Toast.makeText(context, "Air plane mode - $isTurnOn", Toast.LENGTH_LONG).show()
            Log.i("AirPlanMode: ", "Air plane mode - $isTurnOn")
        }
    }
}