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

        val pageMargin = resources.getDimension(R.dimen.learn_material_pageMargin)
        //pager.pageMargin = pageMargin.toInt()
        pager.adapter = Adapter(supportFragmentManager)

        pager.setPageTransformer(true, { page, position ->
            println(position)

            println("scrollx ${pager.scrollX}")
            if (position in (0f..1f)) {
                println("ff ${pager.width} ${page.width} ${page.paddingLeft} ${page.x}")
                val scale = SCALE_THRESHOLD + (1 - SCALE_THRESHOLD) * (1 - position)
                page.translationX = (-(pager.width) / 2 - page.width * scale / 2) * position

                page.scaleX = scale
                page.scaleY = scale
                println("scale $scale translation ${page.translationX} ${page.width * scale} ${page.left} ${page.x}")
            }
        })
    }

    override fun createComponent(module: MaterialModule) = component.createComponent(module)

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int) = createMaterialFragment(position)

        override fun getCount() = 2

    }

}