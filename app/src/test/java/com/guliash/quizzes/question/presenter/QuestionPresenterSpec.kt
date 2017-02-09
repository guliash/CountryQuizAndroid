package com.guliash.quizzes.question.presenter

import com.guliash.quizzes.question.game.StubGame
import com.guliash.quizzes.question.game.StubGamepad
import com.guliash.quizzes.question.view.StubQuestionView
import io.reactivex.schedulers.TestScheduler
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(JUnitPlatform::class)
class QuestionPresenterSpec : Spek({
    describe("presenter") {

        var view = StubQuestionView()
        var game = StubGame()
        var gamepad = StubGamepad()
        var scheduler = TestScheduler()
        var presenter: QuestionPresenter = QuestionPresenter(0, game, gamepad, scheduler, scheduler)

        beforeEachTest {
            view = StubQuestionView()
            game = StubGame()
            gamepad = StubGamepad()
            scheduler = TestScheduler()
            presenter = QuestionPresenter(0, game, gamepad, scheduler, scheduler)
        }

        on("bind") {

            it("without exceptions") {
                presenter.bind(view)

                scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

                presenter.unbind()
            }

        }
    }
})