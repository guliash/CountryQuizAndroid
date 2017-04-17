package com.guliash.quizzes.game.questions.answer.view

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.game.questions.answer.model.Answer
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