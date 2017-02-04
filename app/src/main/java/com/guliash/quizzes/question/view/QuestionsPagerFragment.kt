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

fun createQuestionsPager(): QuestionsPagerFragment = QuestionsPagerFragment()

class QuestionsPagerFragment : Fragment() {

    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.questions_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)
        println(pager)

        pager.adapter = Adapter(fragmentManager)
    }

    class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = createQuestionFragment(position)

        override fun getCount(): Int = Integer.MAX_VALUE
    }
}