package com.guliash.quizzes.question.model

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma

data class Question(val id: String, val question: String = "", val answers: List<Answer>,
                    val image: Image, val enigma: Enigma)