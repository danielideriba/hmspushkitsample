package com.huawei.hmspushkitsample.pushService

import android.content.Intent
import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import java.util.*

class DemoHmsMessageService: HmsMessageService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Log.i("TAG", "Receive token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        Log.i(TAG, "LLamó el onMessageReceived")
        if (message == null) {
            Log.e(TAG, "Llegó una mensaje nula!")
            return
        }
        // getCollapseKey() Obtains the classification identifier (collapse key) of a message.
        // getData() Obtains valid content data of a message.
        // getMessageId() Obtains the ID of a message.
        // getMessageType() Obtains the type of a message.
        // getNotification() Obtains the notification data instance from a message.
        // getOriginalUrgency() Obtains the original priority of a message.
        // getSentTime() Obtains the time when a message is sent from the server.
        // getTo() Obtains the recipient of a message.
        Log.i(TAG, """getCollapseKey: ${message.collapseKey}
            getData: ${message.data}
            getFrom: ${message.from}
            getTo: ${message.to}
            getMessageId: ${message.messageId}
            getMessageType: ${message.messageType}
            getSendTime: ${message.sentTime}
            getTtl: ${message.ttl}
            getSendMode: ${message.sendMode}
            getReceiptMode: ${message.receiptMode}
            getOriginalUrgency: ${message.originalUrgency}
            getUrgency: ${message.urgency}
            getToken: ${message.token}""".trimIndent())


        // getBody() Obtains the displayed content of a message
        // getTitle() Obtains the title of a message
        // getTitleLocalizationKey() Obtains the key of the displayed title of a notification message
        // getTitleLocalizationArgs() Obtains variable parameters of the displayed title of a message
        // getBodyLocalizationKey() Obtains the key of the displayed content of a message
        // getBodyLocalizationArgs() Obtains variable parameters of the displayed content of a message
        // getIcon() Obtains icons from a message
        // getSound() Obtains the sound from a message
        // getTag() Obtains the tag from a message for message overwriting
        // getColor() Obtains the colors of icons in a message
        // getClickAction() Obtains actions triggered by message tapping
        // getChannelId() Obtains IDs of channels that support the display of messages
        // getImageUrl() Obtains the image URL from a message
        // getLink() Obtains the URL to be accessed from a message
        // getNotifyId() Obtains the unique ID of a message
        val notification = message.notification
        if (notification != null) {
            Log.i(TAG, """
                getTitle: ${notification.title}
                getTitleLocalizationKey: ${notification.titleLocalizationKey}
                getTitleLocalizationArgs: ${Arrays.toString(notification.titleLocalizationArgs)}
                getBody: ${notification.body}
                getBodyLocalizationKey: ${notification.bodyLocalizationKey}
                getBodyLocalizationArgs: ${Arrays.toString(notification.bodyLocalizationArgs)}
                getIcon: ${notification.icon}                
                getImageUrl: ${notification.imageUrl}
                getSound: ${notification.sound}
                getTag: ${notification.tag}
                getColor: ${notification.color}
                getClickAction: ${notification.clickAction}
                getIntentUri: ${notification.intentUri}
                getChannelId: ${notification.channelId}
                getLink: ${notification.link}
                getNotifyId: ${notification.notifyId}
                isDefaultLight: ${notification.isDefaultLight}
                isDefaultSound: ${notification.isDefaultSound}
                isDefaultVibrate: ${notification.isDefaultVibrate}
                getWhen: ${notification.`when`}
                getLightSettings: ${Arrays.toString(notification.lightSettings)}
                isLocalOnly: ${notification.isLocalOnly}
                getBadgeNumber: ${notification.badgeNumber}
                isAutoCancel: ${notification.isAutoCancel}
                getImportance: ${notification.importance}
                getTicker: ${notification.ticker}
                getVibrateConfig: ${notification.vibrateConfig}
                getVisibility: ${notification.visibility}""".trimIndent())
        }
        val intent = Intent()
        intent.action = CODELABS_ACTION
        intent.putExtra("method", "onMessageReceived")
        intent.putExtra("msg", "onMessageReceived called, message id:" + message.messageId + ", payload data:"
                + message.data)
        sendBroadcast(intent)
        val judgeWhetherIn10s = false

        // Si los mensajes no se procesan en 10 segundos, la aplicación debe usar WorkManager para procesar
        if (judgeWhetherIn10s) {
            startWorkManagerJob(message)
        } else {
            // Procesar mensaje dentro 10s
            processWithin10s(message)
        }
    }

    private fun startWorkManagerJob(message: RemoteMessage?) {
        Log.d(TAG, "Comenzó la nueva tarea... procesando " + message)
    }

    private fun processWithin10s(message: RemoteMessage?) {
        Log.d(TAG, "Procesando..."+message)
    }

    companion object {
        private const val TAG: String = "DemoHmsMessageService"
        private const val CODELABS_ACTION: String = "com.huawei.codelabpush.action"
    }
}