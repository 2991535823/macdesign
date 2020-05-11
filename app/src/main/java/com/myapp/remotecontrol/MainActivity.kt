package com.myapp.remotecontrol

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.myapp.remotecontrol.Util.HttpUtil
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity(),Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        led_switch.setOnClickListener {
            HttpUtil.sendLedRequest(this)
            led_switch.isClickable=false
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        led_switch.isClickable=true
        runOnUiThread { Toast.makeText(this,"请检查网络连接",Toast.LENGTH_SHORT).show() }

    }

    @SuppressLint("ResourceAsColor")
    override fun onResponse(call: Call, response: Response) {
        led_switch.isClickable=true
        val res=response.body?.string()
        if (res!=null){
            if (res.length<=3){
                runOnUiThread {
                    led_switch.text=res
                    when(res){
                        "ON"->led_layout.setBackgroundResource(R.color.ON)
                        "OFF"->led_layout.setBackgroundResource(R.color.OFF)
                        else->{
                            led_switch.text="error"
                        led_layout.setBackgroundResource(R.color.colorAccent)}
                    }
                }
            }else{
                runOnUiThread {
                    Toast.makeText(this,"请连接指定WIFI",Toast.LENGTH_SHORT).show()
                }
            }

        }




    }
}
