package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable

interface AnswerGenerationStrategy {
    fun generate(enigma: Enigma): List<Answer>
}