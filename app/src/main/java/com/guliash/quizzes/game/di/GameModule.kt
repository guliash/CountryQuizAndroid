package com.guliash.quizzes.game.di

import com.guliash.quizzes.core.services.ConnectivityService
import com.guliash.quizzes.core.services.ConnectivityServiceImpl
import com.guliash.quizzes.core.services.ConnectivityStatusProvider
import com.guliash.quizzes.game.GameActivity
import com.guliash.quizzes.game.Gamepad
import com.guliash.quizzes.game.GamepadImpl
import com.guliash.quizzes.question.presenter.QuestionPresenter
import dagger.Module
import dagger.Provides

@Module
class GameModule(private val activity: GameActivity,
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