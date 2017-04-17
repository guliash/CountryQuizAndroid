package com.guliash.quizzes.game.questions.question.view

import com.guliash.quizzes.game.questions.question.model.Question
import com.guliash.quizzes.game.questions.question.model.Verdict
import io.reactivex.Observable

interface QuestionView {

    fun showProgress()

    fun hideProgress()

    fun showQuestion(question: Question)

    fun hideQuestion()

    fun showError(error: String)

    fun hideError()

    fun showVerdict(verdict: Verdict, questionId: String)

    fun answers(): Observable<Int>

    fun retries(): Observable<Any>

    fun imageSelections(): Observable<Any>

}