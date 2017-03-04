package com.guliash.quizzes.answer.presenter

import com.guliash.quizzes.answer.di.AnswerScope
import com.guliash.quizzes.answer.di.QuestionId
import com.guliash.quizzes.answer.view.AnswerView
import com.guliash.quizzes.core.di.rx.IO
import com.guliash.quizzes.core.di.rx.Main
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.Gamepad
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Scheduler
import javax.inject.Inject

@AnswerScope
class AnswerPresenter @Inject constructor(private @QuestionId val questionId: String,
                                          private val verdict: Verdict,
                                          private val gamepad: Gamepad,
                                          private val game: Game,
                                          private @IO val workScheduler: Scheduler,
                                          private @Main val postScheduler: Scheduler,
                                          private val actionsDelegate: ActionsDelegate) : Presenter<AnswerView>() {

    override fun bind(view: AnswerView) {
        super.bind(view)

        if (verdict.correct) {
            subscribe(
                    game.enigma(questionId)
                            .subscribeOn(workScheduler)
                            .observeOn(postScheduler)
                            .subscribe { enigma ->
                                view.showCorrectAnswer(verdict.answer)
                                view.showEnigma(enigma)
                            }
            )
        } else {
            view.hideEnigma()
            view.showWrongAnswer(verdict.answer)
        }

        subscribe(
                view.tryAgain().subscribe { ø -> view.close() },
                view.next().subscribe { ø ->
                    gamepad.needNext()
                    view.close()
                },
                view.showOnMap().switchMap { ø ->
                    game.enigma(questionId)
                            .subscribeOn(workScheduler)
                            .observeOn(postScheduler)
                            .toObservable()
                }.subscribe { enigma ->
                    actionsDelegate.showMap(enigma.position!!)
                }
        )
    }

}