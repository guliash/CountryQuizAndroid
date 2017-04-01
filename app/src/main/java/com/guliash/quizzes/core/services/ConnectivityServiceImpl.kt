package com.guliash.quizzes.core.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ConnectivityServiceImpl @Inject constructor(val context: Context) : ConnectivityService {

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val statusSubject: BehaviorSubject<ConnectivityStatusProvider.ConnectionStatus> = BehaviorSubject.create()
    private val receiver: MyReceiver = MyReceiver()

    override fun connectionStatus(): Observable<ConnectivityStatusProvider.ConnectionStatus> = statusSubject

    override fun register() {
        context.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun unregister() {
        context.unregisterReceiver(receiver)
    }

    private fun getStatus(): ConnectivityStatusProvider.ConnectionStatus {
        val connected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        if (connected)
            return ConnectivityStatusProvider.ConnectionStatus.CONNECTED
        else
            return ConnectivityStatusProvider.ConnectionStatus.NOT_CONNECTED
    }

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val status = getStatus()
            if (statusSubject.hasValue()) {
                if (statusSubject.value != status) {
                    statusSubject.onNext(status)
                }
            } else {
                statusSubject.onNext(status);
            }
        }
    }
}