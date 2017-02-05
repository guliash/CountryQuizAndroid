package com.guliash.quizzes.question.game

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable
import io.reactivex.Single

class StubGameImpl : Game {
    override fun answers(): Observable<Unit> {
        return Observable.empty()
    }

    companion object {
        val fakeQuestion: Question = Question("test question",
                listOf(), "test url");
    }

    override fun answer(question: Question) {

    }

    override fun question(which: Int): Single<Question> {
        return Single.just(fakeQuestion)
    }

}