package com.guliash.quizzes.core.services

import io.reactivex.Observable

interface ConnectivityStatusProvider {
    enum class ConnectionStatus {
        CONNECTED, NOT_CONNECTED
    }

    fun connectionStatus(): Observable<ConnectionStatus>
}