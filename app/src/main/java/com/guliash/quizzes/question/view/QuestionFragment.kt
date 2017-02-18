package com.guliash.quizzes.question.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.BindViews
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guliash.quizzes.R
import com.guliash.quizzes.answer.di.AnswerModule
import com.guliash.quizzes.answer.di.ComponentProvider
import com.guliash.quizzes.answer.view.createAnswerFragment
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.core.rx.RxView
import com.guliash.quizzes.question.QuestionUtils
import com.guliash.quizzes.question.di.QuestionComponent
import com.guliash.quizzes.question.di.QuestionModule
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import com.guliash.quizzes.question.presenter.QuestionPresenter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

val WHICH_QUESTION_ARG = "which_question_arg";

fun createQuestionFragment(whichQuestion: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt(WHICH_QUESTION_ARG, whichQuestion)
    fragment.arguments = bundle
    return fragment
}

class QuestionFragment : Fragment(), QuestionView, ComponentProvider {

    private var whichQuestion: Int = 0

    @BindView(R.id.image)
    lateinit var questionImageView: ImageView

    @BindView(R.id.attribution)
    lateinit var attributionTextView: TextView

    @BindViews(R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4)
    lateinit var answerButtons: Array<Button>

    @Inject
    lateinit var presenter: QuestionPresenter

    @Inject
    lateinit var utils: QuestionUtils

    lateinit var questionComponent: QuestionComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        whichQuestion = arguments.getInt(WHICH_QUESTION_ARG)

        questionComponent = QuizzesApplication.application(context).gameComponent!!.plus(QuestionModule(whichQuestion))
        questionComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.question, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        attributionTextView.movementMethod = LinkMovementMethod.getInstance()

        presenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.unbind()
    }

    override fun create(module: AnswerModule) = questionComponent.plus(module)

    override fun answers(): Observable<Int> {
        val clicks: MutableList<Observable<Int>> = ArrayList()

        for ((index, button) in answerButtons.withIndex()) {
            clicks.add(RxView.clicks(button).map { ø -> index })
        }

        return Observable.merge(clicks)
    }

    override fun showQuestion(question: Question) {
        Glide.with(this)
                .load(question.image.url)
                .centerCrop()
                .into(questionImageView)


        for ((index, answer) in question.answers.withIndex()) {
            answerButtons[index].text = answer.text
        }

        attributionTextView.setText(utils.buildAttribution(question.image.attribution), TextView.BufferType.SPANNABLE)

    }

    override fun showError(error: String) {
        println("Show error")
    }

    override fun showVerdict(verdict: Verdict, questionId: String) {
        createAnswerFragment(verdict, questionId).show(childFragmentManager, null)
    }

}