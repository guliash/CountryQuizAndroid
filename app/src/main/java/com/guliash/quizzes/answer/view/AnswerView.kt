package com.guliash.quizzes.answer.view

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable

interface AnswerView {
    fun showWrongAnswer(answer: Answer)

    fun showCorrectAnswer(answer: Answer)

    fun showEnigma(enigma: Enigma)

    fun hideEnigma()

    fun close()

    fun tryAgain(): Observable<Unit>

    fun next(): Observable<Unit>

    fun showOnMap(): Observable<Unit>
}