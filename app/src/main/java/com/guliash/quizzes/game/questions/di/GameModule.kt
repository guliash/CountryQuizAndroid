package com.guliash.quizzes.game.questions.di

import com.guliash.quizzes.core.services.ConnectivityService
import com.guliash.quizzes.core.services.ConnectivityServiceImpl
import com.guliash.quizzes.core.services.ConnectivityStatusProvider
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.questions.QuestionsGameActivity
import com.guliash.quizzes.game.questions.question.presenter.QuestionPresenter
import com.guliash.quizzes.game.questions.service.Gamepad
import com.guliash.quizzes.game.questions.service.GamepadImpl
import dagger.Module
import dagger.Provides

@Module
class GameModule(private val activity: QuestionsGameActivity,
                 private val questionCommander: QuestionPresenter.Commander) {

    @Provides
    @GameScope
    fun gamepad(): Gamepad = GamepadImpl()

    @Provides
    @GameScope
    fun connectivityService(): ConnectivityService = ConnectivityServiceImpl(activity)

    @Provides
    @GameScope
    fun connectivityStatusProvider(connectivityService: ConnectivityService): ConnectivityStatusProvider = connectivityService

    @Provides
    @GameScope
    fun questionCommander() = questionCommander
}