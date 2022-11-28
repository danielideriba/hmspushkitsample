package com.huawei.hmspushkitsample.pushService

import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

class DemoHmsMessageService: HmsMessageService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Log.i("TAG", "Receive token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)

        Log.i("TAG", "Receive message: ${message?.messageId}")
    }


}