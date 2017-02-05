package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable

interface QuestionView {

    fun showQuestion(question: Question)

    fun showError(error: String)

    fun answers(): Observable<Int>

}