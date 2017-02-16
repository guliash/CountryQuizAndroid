package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable

interface QuestionView {

    fun showQuestion(question: Question)

    fun showError(error: String)

    fun showVerdict(verdict: Verdict)

    fun answers(): Observable<Int>

}