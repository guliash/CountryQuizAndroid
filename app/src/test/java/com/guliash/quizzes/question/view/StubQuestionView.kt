package com.guliash.quizzes.question.view

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Completable
import io.reactivex.Observable

class StubQuestionView : QuestionView {
    override fun showWrongAnswer(answer: Answer) {

    }

    override fun showCorrectAnswer(answer: Answer): Completable {
        return Completable.complete()
    }

    override fun answers(): Observable<Int> = Observable.empty()

    override fun showQuestion(enigma: Enigma) {

    }

    override fun showError(error: String) {

    }
}