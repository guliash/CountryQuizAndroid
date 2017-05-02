package com.guliash.quizzes.learn

import android.os.Bundle
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.ui.activity.BaseActivity
import com.guliash.quizzes.learn.details.DetailsComponent
import com.guliash.quizzes.learn.details.DetailsComponentProvider
import com.guliash.quizzes.learn.details.DetailsModule
import com.guliash.quizzes.learn.details.view.createDetailsFragment
import com.guliash.quizzes.learn.preview.PreviewComponentProvider
import com.guliash.quizzes.learn.preview.PreviewModule
import com.guliash.quizzes.learn.preview.presenter.PreviewPresenter

class LearnActivity : BaseActivity(), PreviewComponentProvider, DetailsComponentProvider {

    lateinit var component: LearnComponent

    val previewCommander = object : PreviewPresenter.Commander {
        override fun onPreviewSelected(materialId: String) {
            supportFragmentManager.beginTransaction()
                    .hide(supportFragmentManager.findFragmentByTag(TAG))
                    .add(R.id.container, createDetailsFragment(materialId))
                    .addToBackStack(null)
                    .commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component = QuizzesApplication.application(this).appComponent.plus(LearnModule(previewCommander))

        super.onCreate(savedInstanceState)

        setContentView(R.layout.learn_activity)
        ButterKnife.bind(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, createLearnPagerFragment(), TAG)
                    .commit()
        }
    }

    override fun createComponent(module: PreviewModule) = component.createComponent(module)

    override fun createComponent(module: DetailsModule): DetailsComponent =
            component.createComponent(module)

}