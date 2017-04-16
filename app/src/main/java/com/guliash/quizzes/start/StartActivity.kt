package com.guliash.quizzes.start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.ui.activity.BaseActivity
import com.guliash.quizzes.game.GameActivity
import com.guliash.quizzes.start.di.StartModule
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import javax.inject.Inject

class StartActivity : BaseActivity(), StartView, ActionsDelegate {

    @BindView(R.id.learn)
    lateinit var learnButton: Button

    @BindView(R.id.game)
    lateinit var gameButton: Button

    @Inject
    lateinit var presenter: StartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        QuizzesApplication.application(this).appComponent.plus(StartModule(this)).inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.start_activity)

        ButterKnife.bind(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun learns(): Observable<Any> = RxView.clicks(learnButton)

    override fun plays(): Observable<Any> = RxView.clicks(gameButton)

    override fun showLearn() {

    }

    override fun showPlay() {
        startActivity(Intent(this, GameActivity::class.java))
    }
}