package com.guliash.quizzes.game.questions

import android.content.Intent
import android.os.Bundle
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.services.ConnectivityService
import com.guliash.quizzes.core.ui.activity.BaseActivity
import com.guliash.quizzes.game.questions.di.GameComponent
import com.guliash.quizzes.game.questions.di.GameModule
import com.guliash.quizzes.game.questions.question.presenter.QuestionPresenter
import com.guliash.quizzes.game.questions.question.view.createQuestionsPager
import com.guliash.quizzes.image.FullscreenImageActivity
import com.guliash.quizzes.image.IMAGE_URL_EXTRA
import javax.inject.Inject

class QuestionsGameActivity : BaseActivity() {

    lateinit var gameComponent: GameComponent

    @Inject
    lateinit var connectivityService: ConnectivityService

    val questionCommander = object : QuestionPresenter.Commander {
        override fun onImageSelected(imageUrl: String) {
            val intent: Intent = Intent(this@QuestionsGameActivity, FullscreenImageActivity::class.java)
            intent.putExtra(IMAGE_URL_EXTRA, imageUrl)
            startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        gameComponent = QuizzesApplication.application(this).appComponent.plus(
                GameModule(this, questionCommander)
        )
        gameComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.game_activity)

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
