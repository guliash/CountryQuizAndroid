package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable

class StubQuestionView: QuestionView {
    override fun answers(): Observable<Int> = Observable.empty()

    override fun showQuestion(question: Question) {

    }

    override fun showError(error: String) {

    }
}