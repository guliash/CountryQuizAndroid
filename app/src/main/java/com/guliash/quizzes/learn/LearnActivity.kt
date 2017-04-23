package com.guliash.quizzes.learn

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.ui.activity.BaseActivity
import com.guliash.quizzes.learn.di.LearnComponent
import com.guliash.quizzes.learn.di.MaterialComponentProvider
import com.guliash.quizzes.learn.di.MaterialModule
import com.guliash.quizzes.learn.view.createMaterialFragment

private val SCALE_THRESHOLD = 0.5f

class LearnActivity : BaseActivity(), MaterialComponentProvider {

    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    lateinit var component: LearnComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = QuizzesApplication.application(this).appComponent.plus()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.learn_activity)
        ButterKnife.bind(this)

        pager.adapter = Adapter(supportFragmentManager)

        pager.setPageTransformer(true, { page, position ->
            if (position > 0f && position <= 1f) {
                val scale = 1 - position + SCALE_THRESHOLD * position
                page.pivotX = 0f
                page.scaleX = scale
                page.scaleY = scale
                page.translationX = -(pager.width + page.width * scale) * position / 2
            } else if (position > 1f) {
                page.pivotX = 0f
                page.scaleX = 1f
                page.scaleY = 1f
                page.translationX = 0f
            }
        })
    }

    override fun createComponent(module: MaterialModule) = component.createComponent(module)

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int) = createMaterialFragment(position)

        override fun getCount() = Int.MAX_VALUE

    }

}