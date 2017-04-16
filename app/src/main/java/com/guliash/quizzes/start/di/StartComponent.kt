package com.guliash.quizzes.start.di

import com.guliash.quizzes.start.StartActivity
import dagger.Subcomponent

@StartScope
@Subcomponent(modules = arrayOf(StartModule::class))
interface StartComponent {
    fun inject(startActivity: StartActivity)
}