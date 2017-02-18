package com.guliash.quizzes.answer.view

import com.guliash.quizzes.answer.model.Answer
import io.reactivex.Observable

interface AnswerView {
    fun showWrongAnswer(answer: Answer)

    fun showCorrectAnswer(answer: Answer)

    fun close()

    fun tryAgain(): Observable<Unit>

    fun next(): Observable<Unit>
}