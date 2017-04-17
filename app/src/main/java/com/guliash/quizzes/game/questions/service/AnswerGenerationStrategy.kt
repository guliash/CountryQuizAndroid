package com.guliash.quizzes.game.questions.service

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.game.questions.answer.model.Answer

interface AnswerGenerationStrategy {
    fun generate(place: Place): List<Answer>
}