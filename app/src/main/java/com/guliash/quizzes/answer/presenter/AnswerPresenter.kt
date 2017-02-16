package com.guliash.quizzes.answer.presenter

import com.guliash.quizzes.answer.view.AnswerView
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.Gamepad
import com.guliash.quizzes.question.model.Verdict
import javax.inject.Inject

class AnswerPresenter @Inject constructor(val verdict: Verdict, val gamepad: Gamepad) : Presenter<AnswerView>() {

    override fun bind(view: AnswerView) {
        super.bind(view)

        if (verdict.correct) view.showCorrectAnswer(verdict.answer) else view.showWrongAnswer(verdict.answer)
    }

}