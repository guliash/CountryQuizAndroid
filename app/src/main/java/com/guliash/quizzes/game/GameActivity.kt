package com.guliash.quizzes.game

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.game.di.GameModule
import com.guliash.quizzes.question.view.createQuestionsPager

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        QuizzesApplication.application(this).gameComponent = QuizzesApplication.application(this)
                .appComponent.plus(GameModule())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, createQuestionsPager()).commit()
        }
    }

    override fun onDestroy() {
        QuizzesApplication.application(this).gameComponent = null
        super.onDestroy()
    }
}
