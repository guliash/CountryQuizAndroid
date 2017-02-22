package com.guliash.quizzes.game

import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable

interface Repository {

    fun questions(): Observable<Question>

}