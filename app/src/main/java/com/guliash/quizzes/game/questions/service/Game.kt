package com.guliash.quizzes.game.questions.service

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.game.questions.answer.model.Answer
import com.guliash.quizzes.game.questions.question.model.Question
import com.guliash.quizzes.game.questions.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single

interface Game {

    fun questions(): Observable<Question>

    fun question(which: Int): Single<Question>

    fun answer(question: Question, answer: Answer): Single<Verdict>

    fun place(id: String): Single<Place>

}