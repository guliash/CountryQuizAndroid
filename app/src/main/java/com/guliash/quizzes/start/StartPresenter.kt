package com.guliash.quizzes.start

import com.guliash.quizzes.core.mvp.Presenter
import javax.inject.Inject

class StartPresenter @Inject constructor(val actionsDelegate: ActionsDelegate) : Presenter<StartView>() {

    override fun bind(view: StartView) {
        super.bind(view)

        subscribe(
                view.learns().subscribe { actionsDelegate.showLearn() },
                view.plays().subscribe { actionsDelegate.showPlay() }
        )
    }
}