package com.guliash.quizzes.question.model

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class QuestionSpec : Spek({
    describe("question model") {
        val question = Question(answers = arrayListOf())
    }
})