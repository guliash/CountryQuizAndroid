package com.guliash.quizzes.question.view

import com.guliash.quizzes.question.model.Question

interface QuestionView {

    fun showQuestion(question: Question)

    fun showError(error: String)

}