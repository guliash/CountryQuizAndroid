package com.guliash.quizzes.learn

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.core.ui.fragment.BaseFragment
import com.guliash.quizzes.learn.preview.view.createMaterialFragment

private const val SCALE_THRESHOLD = 0.5f
val TAG = LearnPagerFragment::class.java.name

fun createLearnPagerFragment() = LearnPagerFragment()

class LearnPagerFragment : BaseFragment() {

    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.learn_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view!!)

        pager.adapter = Adapter(fragmentManager)

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

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = createMaterialFragment(position)

        override fun getCount() = Int.MAX_VALUE

    }

}