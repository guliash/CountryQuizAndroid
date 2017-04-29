package com.guliash.quizzes.learn.details.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.learn.details.DetailsScope
import com.guliash.quizzes.learn.details.view.DetailsView
import javax.inject.Inject

@DetailsScope
class DetailsPresenter @Inject constructor() : Presenter<DetailsView>() {

    override fun bind(view: DetailsView) {
        super.bind(view)
    }

}