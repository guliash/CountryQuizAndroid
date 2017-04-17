package com.guliash.quizzes.game.questions.answer.presenter

import com.guliash.quizzes.core.app.di.rx.IO
import com.guliash.quizzes.core.app.di.rx.Main
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.questions.answer.di.AnswerScope
import com.guliash.quizzes.game.questions.answer.di.QuestionId
import com.guliash.quizzes.game.questions.answer.view.AnswerView
import com.guliash.quizzes.game.questions.question.model.Verdict
import com.guliash.quizzes.game.questions.service.Game
import com.guliash.quizzes.game.questions.service.Gamepad
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
                    game.place(questionId)
                            .subscribeOn(workScheduler)
                            .observeOn(postScheduler)
                            .subscribe { place ->
                                view.showCorrectAnswer(verdict.answer)
                                view.showPlace(place)
                            }
            )
        } else {
            view.hidePlace()
            view.showWrongAnswer(verdict.answer)
        }

        subscribe(
                view.tryAgain().subscribe { view.close() },
                view.next().subscribe {
                    gamepad.needNext()
                    view.close()
                },
                view.showOnMap().switchMap {
                    game.place(questionId)
                            .subscribeOn(workScheduler)
                            .observeOn(postScheduler)
                            .toObservable()
                }.subscribe { place ->
                    actionsDelegate.showMap(place.position)
                }
        )
    }

}