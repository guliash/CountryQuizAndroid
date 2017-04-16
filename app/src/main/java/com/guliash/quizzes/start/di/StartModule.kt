package com.guliash.quizzes.start.di

import com.guliash.quizzes.start.ActionsDelegate
import dagger.Module
import dagger.Provides

@Module
class StartModule(val actionsDelegate: ActionsDelegate) {

    @Provides
    @StartScope
    fun actionsDelegate(): ActionsDelegate = actionsDelegate

}