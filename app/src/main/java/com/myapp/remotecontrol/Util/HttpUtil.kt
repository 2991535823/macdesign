package com.myapp.remotecontrol.Util

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

object  HttpUtil {
    fun sendLedRequest(callback: Callback){
        val client= OkHttpClient()
        val request= Request.Builder().url("http://www.example.com/LED").build()
        client.newCall(request).enqueue(callback)
    }
}