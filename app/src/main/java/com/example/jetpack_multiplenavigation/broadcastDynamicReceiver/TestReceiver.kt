package com.example.jetpack_multiplenavigation.broadcastDynamicReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "ACTION_TEST") {
            println("Received test intent.")
            Toast.makeText(context, "Received test intent.", Toast.LENGTH_LONG).show()
        }
    }
}