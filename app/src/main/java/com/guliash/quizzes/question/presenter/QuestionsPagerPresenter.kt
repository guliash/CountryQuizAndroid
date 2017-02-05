package com.guliash.quizzes.question.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.question.view.QuestionsPagerView
import javax.inject.Inject

@GameScope
class QuestionsPagerPresenter @Inject constructor(val game: Game) : Presenter<QuestionsPagerView>() {

    override fun bind(view: QuestionsPagerView) {
        super.bind(view)

        subscribe(game.answers().subscribe { view.showNextQuestion() })
    }

    override fun unbind() {
        super.unbind()
    }

}