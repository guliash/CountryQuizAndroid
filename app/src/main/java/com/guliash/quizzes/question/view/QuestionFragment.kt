package com.guliash.quizzes.question.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.question.di.QuestionModule
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.presenter.QuestionPresenter
import javax.inject.Inject

val WHICH_QUESTION_ARG = "which_question_arg";

fun createQuestionFragment(whichQuestion: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt(WHICH_QUESTION_ARG, whichQuestion)
    fragment.arguments = bundle
    return fragment
}

class QuestionFragment : Fragment(), QuestionView {
    private var whichQuestion: Int = 0

    @BindView(R.id.image)
    lateinit var questionImageView: ImageView

    @Inject
    lateinit var presenter: QuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whichQuestion = arguments.getInt(WHICH_QUESTION_ARG)

        QuizzesApplication.application(context).gameComponent!!.plus(QuestionModule(whichQuestion))
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.question, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        presenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.unbind()
    }

    override fun showQuestion(question: Question) {
        Glide.with(this)
                .load(question.imageUrl)
                .centerCrop()
                .into(questionImageView)
    }

    override fun showError(error: String) {

    }

}