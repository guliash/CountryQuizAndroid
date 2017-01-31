package com.guliash.quizzes.question

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guliash.quizzes.R

val WHICH_QUESTION_ARG = "which_question_arg";

fun createQuestionFragment(whichQuestion: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt(WHICH_QUESTION_ARG, whichQuestion)
    fragment.arguments = bundle
    return fragment
}

class QuestionFragment : Fragment() {

    private var whichQuestion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whichQuestion = arguments.getInt(WHICH_QUESTION_ARG)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.question, container, false);
    }

}