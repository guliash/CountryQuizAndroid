package com.guliash.quizzes.question.view

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Place
import io.reactivex.Completable
import io.reactivex.Observable

class StubQuestionView : QuestionView {
    override fun showWrongAnswer(answer: Answer) {

    }

    override fun showCorrectAnswer(answer: Answer): Completable {
        return Completable.complete()
    }

    override fun answers(): Observable<Int> = Observable.empty()

    override fun showQuestion(place: Place) {

    }

    override fun showError(error: String) {

    }
}