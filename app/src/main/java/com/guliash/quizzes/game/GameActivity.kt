package com.guliash.quizzes.game

import android.os.Bundle
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.services.ConnectivityService
import com.guliash.quizzes.core.ui.activity.BaseActivity
import com.guliash.quizzes.game.di.GameComponent
import com.guliash.quizzes.game.di.GameModule
import com.guliash.quizzes.question.view.createQuestionsPager
import javax.inject.Inject

class GameActivity : BaseActivity() {

    lateinit var gameComponent: GameComponent

    @Inject
    lateinit var connectivityService: ConnectivityService

    override fun onCreate(savedInstanceState: Bundle?) {
        gameComponent = QuizzesApplication.application(this).appComponent.plus(GameModule(this))
        gameComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, createQuestionsPager()).commit()
        }

        connectivityService.register()
    }

    override fun onDestroy() {
        connectivityService.unregister()
        super.onDestroy()
    }
}
