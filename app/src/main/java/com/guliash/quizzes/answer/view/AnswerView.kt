package com.guliash.quizzes.answer.view

import com.guliash.quizzes.answer.model.Answer

interface AnswerView {
    fun showWrongAnswer(answer: Answer)

    fun showCorrectAnswer(answer: Answer)
}