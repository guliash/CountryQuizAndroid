package com.guliash.quizzes

import junit.framework.Assert.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class ExampleSpec : Spek({
    describe("types") {
        on("any? and int?") {
            val b: Int? = null
            it("should be instance of?") {
                assertEquals(true, b is Any?)
            }
        }
    }
})