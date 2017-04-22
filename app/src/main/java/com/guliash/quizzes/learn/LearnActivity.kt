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

class LearnActivity : BaseActivity(), MaterialComponentProvider {

    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    lateinit var component: LearnComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = QuizzesApplication.application(this).appComponent.plus()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.learn_activity)
        ButterKnife.bind(this)

        pager.pageMargin = resources.getDimension(R.dimen.learn_material_pageMargin).toInt()
        pager.adapter = Adapter(supportFragmentManager)
    }

    override fun createComponent(module: MaterialModule) = component.createComponent(module)

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int) = createMaterialFragment(position)

        override fun getCount() = Int.MAX_VALUE

    }

}