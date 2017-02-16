package com.guliash.quizzes.answer.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.answer.di.AnswerModule
import com.guliash.quizzes.answer.di.ComponentProvider
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.answer.presenter.AnswerPresenter
import com.guliash.quizzes.question.model.Verdict
import javax.inject.Inject

private val VERDICT_EXTRA = "verdict"

fun createAnswerFragment(verdict: Verdict): DialogFragment {
    val args = Bundle()
    args.putParcelable(VERDICT_EXTRA, verdict)
    val fragment = AnswerFragment()
    fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    fragment.arguments = args
    return fragment
}

class AnswerFragment : DialogFragment(), AnswerView {

    @BindView(R.id.verdict)
    lateinit var verdictTextView: TextView

    @Inject
    lateinit var presenter: AnswerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (parentFragment is ComponentProvider) {
            (parentFragment as ComponentProvider)
                    .create(AnswerModule(arguments.getParcelable(VERDICT_EXTRA)))
                    .inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.answer, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        presenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.unbind()
    }

    override fun showCorrectAnswer(answer: Answer) {
        verdictTextView.text = "correct"
    }

    override fun showWrongAnswer(answer: Answer) {
        verdictTextView.text = "wrong"
    }

}
