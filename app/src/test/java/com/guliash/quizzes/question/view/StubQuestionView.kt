package com.guliash.quizzes.question.view

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.question.model.Question
import io.reactivex.Completable
import io.reactivex.Observable

class StubQuestionView : QuestionView {
    override fun showWrongAnswer(answer: Answer) {

    }

    override fun showCorrectAnswer(answer: Answer): Completable {
        return Completable.complete()
    }

    override fun answers(): Observable<Int> = Observable.empty()

    override fun showQuestion(question: Question) {

    }

    override fun showError(error: String) {

    }
}