package com.guliash.quizzes.answer.view

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Place
import io.reactivex.Observable

interface AnswerView {
    fun showWrongAnswer(answer: Answer)

    fun showCorrectAnswer(answer: Answer)

    fun showPlace(place: Place)

    fun hidePlace()

    fun close()

    fun tryAgain(): Observable<Any>

    fun next(): Observable<Any>

    fun showOnMap(): Observable<Any>
}