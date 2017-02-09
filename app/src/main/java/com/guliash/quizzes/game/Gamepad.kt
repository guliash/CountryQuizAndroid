package com.guliash.quizzes.game

import io.reactivex.Observable

interface Gamepad {
    fun nextNotifications(): Observable<Unit>

    fun needNext()
}