package com.example.jetpack_multiplenavigation.webSockets.data.request

import okhttp3.Request

object WebSocketsRequest {
    private const val URL = "wss://s13662.nyc1.piesocket.com/v3/1?api_key=MIxsYnxPHkLvzlxqZzeVlTyTAIj22HyzicAYKt7g&notify_self=1"

    val request = Request.Builder()
        .url(URL)
        .build()
}