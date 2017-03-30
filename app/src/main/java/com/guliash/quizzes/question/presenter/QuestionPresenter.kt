package com.guliash.quizzes.question.presenter

import android.content.Context
import com.guliash.quizzes.R
import com.guliash.quizzes.core.di.rx.IO
import com.guliash.quizzes.core.di.rx.Main
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.view.QuestionView
import io.reactivex.BackpressureStrategy
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@QuestionScope
class QuestionPresenter @Inject constructor(private val whichQuestion: Int,
                                            private val game: Game,
                                            private val context: Context,
                                            private @IO val workScheduler: Scheduler,
                                            private @Main val postScheduler: Scheduler) :
        Presenter<QuestionView>() {

    private lateinit var question: Question

    override fun bind(view: QuestionView) {
        super.bind(view)

        subscribe(
                question()
                        .doOnSubscribe {
                            view.hideQuestion()
                            view.hideError()
                            view.showProgress()
                        }
                        .doOnSuccess { question ->
                            this.question = question
                            view.hideProgress()
                            view.hideError()
                            view.showQuestion(question)
                        }
                        .doOnError {
                            it.printStackTrace()
                            view.hideProgress()
                            view.hideQuestion()
                            view.showError(context.getString(R.string.question_error))
                        }
                        .retryWhen { errors ->
                            errors.switchMap {
                                view.retries().take(1).toFlowable(BackpressureStrategy.DROP)
                            }
                        }
                        .subscribe(),
                view.answers()
                        .observeOn(workScheduler)
                        .concatMap({ whichAnswer ->
                            game.answer(question, question.answers[whichAnswer]).toObservable()
                        })
                        .observeOn(postScheduler)
                        .subscribe({ verdict -> view.showVerdict(verdict, question.place.id) })
        )
    }

    private fun question(): Single<Question> {
        return game.question(whichQuestion)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler);
    }

}