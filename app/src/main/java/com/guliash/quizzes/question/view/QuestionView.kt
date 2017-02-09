package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Answer
import com.guliash.quizzes.question.model.Question
import io.reactivex.Completable
import io.reactivex.Observable

interface QuestionView {

    fun showQuestion(question: Question)

    fun showError(error: String)

    fun showWrongAnswer(answer: Answer)

    fun showCorrectAnswer(answer: Answer): Completable

    fun answers(): Observable<Int>

}