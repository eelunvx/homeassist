package com.axzae.homeassistant.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.axzae.homeassistant.AppController
import com.axzae.homeassistant.model.rest.FCMAndroidRequest
import com.axzae.homeassistant.model.rest.FCMAndroidResponse
import com.axzae.homeassistant.provider.DatabaseManager
import com.axzae.homeassistant.provider.ServiceProvider
import com.google.firebase.iid.FirebaseInstanceId
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {




        val id = intent?.getIntExtra("id", 0)
        val action = intent?.getStringExtra("action")

        val notificationManager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(id!!)

        val fcmAndroidRequest = FCMAndroidRequest(FirebaseInstanceId.getInstance().token)
        fcmAndroidRequest.setAction(action)
        fcmAndroidRequest.setType("clicked")

        val mSharedPref = AppController.getInstance().sharedPref
        val mServers = DatabaseManager.getInstance(context).connections
        val mCurrentServer = mServers[mSharedPref.getInt("connectionIndex", 0)]


        val mCall = ServiceProvider.getApiService(mCurrentServer.baseUrl).postFCMCallback(mCurrentServer.password, fcmAndroidRequest)
        mCall.enqueue(object : Callback<FCMAndroidResponse> {
            override fun onResponse(call: Call<FCMAndroidResponse>, response: Response<FCMAndroidResponse>) {
                Toast.makeText(context, response.body()!!.getMessage(), Toast.LENGTH_SHORT).show()
                Log.d("FCMAndroid", response.body()!!.getMessage())
            }

            override fun onFailure(call: Call<FCMAndroidResponse>, t: Throwable) {

            }
        })
    }




}

