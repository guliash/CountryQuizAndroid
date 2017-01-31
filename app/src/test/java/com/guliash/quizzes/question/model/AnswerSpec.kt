package com.guliash.quizzes.question.model

import junit.framework.Assert.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class AnswerSpec : Spek({
    describe("answer model") {
        val answer = Answer(text = "test", correct = true)

        on("get text") {
            val text = answer.text;

            it("should return same text") {
                assertEquals("test", text)
            }
        }

        on("get correct") {
            val correct = answer.correct;

            it("should return same correct") {
                assertEquals(true, correct)
            }
        }
    }
})