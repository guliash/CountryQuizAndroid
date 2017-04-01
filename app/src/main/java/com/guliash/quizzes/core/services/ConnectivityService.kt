package com.guliash.quizzes.core.services

interface ConnectivityService : ConnectivityStatusProvider {
    fun register()

    fun unregister()
}