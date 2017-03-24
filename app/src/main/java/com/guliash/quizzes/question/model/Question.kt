package com.guliash.quizzes.question.model

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Place

data class Question(val place: Place, val answers: List<Answer>) {
}