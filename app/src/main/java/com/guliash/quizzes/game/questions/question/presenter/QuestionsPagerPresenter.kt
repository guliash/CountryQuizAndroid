package com.guliash.quizzes.game.questions.question.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.questions.question.view.QuestionsPagerView
import com.guliash.quizzes.game.questions.service.Gamepad
import javax.inject.Inject

@GameScope
class QuestionsPagerPresenter @Inject constructor(private val gamepad: Gamepad) : Presenter<QuestionsPagerView>() {

    override fun bind(view: QuestionsPagerView) {
        super.bind(view)

        subscribe(gamepad.nextNotifications().subscribe { view.showNextQuestion() })
    }

    override fun unbind() {
        super.unbind()
    }

}