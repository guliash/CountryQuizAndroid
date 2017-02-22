package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single

interface Game {

    fun questions(): Observable<Question>

    fun question(which: Int): Single<Question>

    fun answer(question: Question, answer: Answer): Single<Verdict>

    fun enigma(questionId: String): Single<Enigma>

}