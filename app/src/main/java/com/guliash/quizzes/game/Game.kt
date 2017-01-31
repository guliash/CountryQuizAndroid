package com.guliash.quizzes.game

import com.guliash.quizzes.question.model.Question
import io.reactivex.Single

interface Game {
    fun question(which: Int): Single<Question>

    fun answer(question: Question)
}