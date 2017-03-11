package com.guliash.quizzes.question.model

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma

data class Question(val enigma: Enigma, val answers: List<Answer>) {
}