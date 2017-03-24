package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Place

interface AnswerGenerationStrategy {
    fun generate(place: Place): List<Answer>
}