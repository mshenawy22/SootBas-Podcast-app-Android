package com.sootbas.sootbasapp

import android.nfc.Tag
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService()
{
    override fun onNewToken(token: String) {
//        super.onNewToken(p0)

Log.d("TAG","The token is refreshed: $token")
    }

}
