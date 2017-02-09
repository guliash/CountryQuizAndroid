package com.guliash.quizzes.question.game

import com.guliash.quizzes.game.Gamepad
import io.reactivex.Observable

class StubGamepad() : Gamepad {
    override fun nextNotifications(): Observable<Unit> {
        return Observable.empty()
    }

    override fun needNext() {
    }

}
