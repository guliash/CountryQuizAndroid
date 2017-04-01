package com.guliash.quizzes.core

import android.app.Application
import android.content.Context
import com.guliash.quizzes.BuildConfig
import com.guliash.quizzes.core.app.di.AppComponent
import com.guliash.quizzes.core.app.di.AppModule
import com.guliash.quizzes.core.app.di.DaggerAppComponent
import timber.log.Timber

class QuizzesApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        fun application(context: Context): QuizzesApplication = (context.applicationContext as QuizzesApplication)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}