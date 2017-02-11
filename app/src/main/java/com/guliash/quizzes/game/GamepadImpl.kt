package com.guliash.quizzes.game

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class GamepadImpl @Inject constructor() : Gamepad {

    private val nextNotifications = PublishSubject.create<Unit>()

    override fun needNext() {
        nextNotifications.onNext(Unit)
    }

    override fun nextNotifications(): Observable<Unit> = nextNotifications
}