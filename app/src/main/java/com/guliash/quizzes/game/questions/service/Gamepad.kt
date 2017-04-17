package com.guliash.quizzes.game.questions.service

import io.reactivex.Observable

interface Gamepad {
    fun nextNotifications(): Observable<Unit>

    fun needNext()
}