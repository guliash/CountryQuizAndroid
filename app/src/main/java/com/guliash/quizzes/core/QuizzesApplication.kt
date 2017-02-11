package com.guliash.quizzes.core

import android.app.Application
import android.content.Context
import com.guliash.quizzes.core.di.AppComponent
import com.guliash.quizzes.core.di.AppModule
import com.guliash.quizzes.core.di.DaggerAppComponent
import com.guliash.quizzes.game.di.GameComponent

class QuizzesApplication : Application() {

    lateinit var appComponent: AppComponent
    var gameComponent: GameComponent? = null

    companion object {
        fun application(context: Context): QuizzesApplication = (context.applicationContext as QuizzesApplication)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}