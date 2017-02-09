package com.guliash.quizzes.question.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.core.rx.IO
import com.guliash.quizzes.core.rx.Main
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.Gamepad
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.view.QuestionView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

@QuestionScope
class QuestionPresenter @Inject constructor(private val whichQuestion: Int,
                                            private val game: Game,
                                            private val gamepad: Gamepad,
                                            @IO val workScheduler: Scheduler,
                                            @Main val postScheduler: Scheduler) :
        Presenter<QuestionView>() {

    private lateinit var question: Question

    override fun bind(view: QuestionView) {
        super.bind(view)

        subscribe(
                question()
                        .doOnSuccess { question -> this.question = question }
                        .subscribe(
                                { question -> view.showQuestion(question) },
                                { error -> view.showError("Sorry, error occurred.") }),
                view.answers()
                        .observeOn(workScheduler)
                        .concatMap({ whichAnswer ->
                            game.answer(question, question.answers[whichAnswer]).toObservable()
                        })
                        .observeOn(postScheduler)
                        .concatMap({ verdict ->
                            if (verdict.correct) {
                                view.showCorrectAnswer(verdict.answer).andThen(Observable.just(verdict))
                            } else {
                                view.showWrongAnswer(verdict.answer)
                                Observable.just(verdict)
                            }
                        })
                        .doOnNext({ verdict -> if (verdict.correct) gamepad.needNext() })
                        .subscribe()
        )
    }

    private fun question(): Single<Question> {
        return game.question(whichQuestion)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler);
    }

}