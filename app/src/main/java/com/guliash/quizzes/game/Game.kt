package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Single

interface Game {

    fun question(which: Int): Single<Question>

    fun answer(question: Question, answer: Answer): Single<Verdict>

}