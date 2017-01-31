package com.guliash.quizzes.game

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guliash.quizzes.R
import com.guliash.quizzes.question.createQuestionsPager

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, createQuestionsPager()).commit()
        }
    }
}
