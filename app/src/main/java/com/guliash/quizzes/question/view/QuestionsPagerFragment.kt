package com.guliash.quizzes.question.view

import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.guliash.quizzes.game.GameActivity
import com.guliash.quizzes.question.presenter.QuestionsPagerPresenter
import javax.inject.Inject

fun createQuestionsPager(): QuestionsPagerFragment = QuestionsPagerFragment()

class QuestionsPagerFragment : BaseFragment(), QuestionsPagerView {
    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    @Inject
    lateinit var presenter: QuestionsPagerPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.questions_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as GameActivity).gameComponent.inject(this)

        ButterKnife.bind(this, view!!)

        presenter.bind(this)

        pager.pageMargin = context.resources.getDimensionPixelOffset(R.dimen.question_PageMargin)
        pager.offscreenPageLimit = 2
        pager.adapter = Adapter(fragmentManager)

    }

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

    override fun showNextQuestion() {
        pager.currentItem += 1
    }

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = createQuestionFragment(position)

        override fun getCount(): Int = Integer.MAX_VALUE
    }
}