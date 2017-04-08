package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
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

    fun retries(): Observable<Unit>

    fun imageSelections(): Observable<Unit>

}