package com.guliash.quizzes.question.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.core.rx.IO
import com.guliash.quizzes.core.rx.Main
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.view.QuestionView
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

@QuestionScope
class QuestionPresenter @Inject constructor(val whichQuestion: Int, val game: Game,
                                            @IO val workScheduler: Scheduler,
                                            @Main val postScheduler: Scheduler) :
        Presenter<QuestionView>() {

    override fun bind(view: QuestionView) {
        super.bind(view)

        subscribe(question().subscribe(
                { question -> view.showQuestion(question) },
                { error -> view.showError("Sorry, error occurred.") })
        )
    }

    private fun question(): Single<Question> {
        return game.question(whichQuestion)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler);
    }

}