package com.guliash.quizzes.game.questions.question.model

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.game.questions.answer.model.Answer

data class Question(val place: Place, val answers: List<Answer>) {
}