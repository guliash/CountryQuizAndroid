package com.guliash.quizzes.question.model

import com.guliash.quizzes.game.model.Place
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class QuestionSpec : Spek({
    describe("question model") {
        val question = Place(answers = arrayListOf())
    }
})