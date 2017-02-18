package com.guliash.quizzes.answer.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.answer.di.AnswerModule
import com.guliash.quizzes.answer.di.ComponentProvider
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.answer.presenter.AnswerPresenter
import com.guliash.quizzes.core.rx.RxView
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable
import javax.inject.Inject

private val VERDICT_EXTRA = "verdict"

fun createAnswerFragment(verdict: Verdict): DialogFragment {
    val args = Bundle()
    args.putParcelable(VERDICT_EXTRA, verdict)
    val fragment = AnswerFragment()
    fragment.arguments = args
    return fragment
}

class AnswerFragment : DialogFragment(), AnswerView {

    @BindView(R.id.title)
    lateinit var verdictTextView: TextView

    @BindView(R.id.try_again)
    lateinit var tryAgainButton: Button

    @BindView(R.id.next)
    lateinit var nextButton: Button

    @Inject
    lateinit var presenter: AnswerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (parentFragment is ComponentProvider) {
            (parentFragment as ComponentProvider)
                    .create(AnswerModule(arguments.getParcelable(VERDICT_EXTRA)))
                    .inject(this)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.answer, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        presenter.bind(this)
    }

    override fun onDestroyView() {
        presenter.unbind()

        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()

        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)

        val window = dialog.window

        window.setLayout((metrics.widthPixels * 0.9).toInt(), WRAP_CONTENT)
    }

    override fun showCorrectAnswer(answer: Answer) {
        tryAgainButton.visibility = GONE
        verdictTextView.setTextColor(ContextCompat.getColor(context, R.color.answer_correctAnswer))
        verdictTextView.text = context.getString(R.string.answer_correctAnswer)
    }

    override fun showWrongAnswer(answer: Answer) {
        verdictTextView.setTextColor(ContextCompat.getColor(context, R.color.answer_wrongAnswer))
        verdictTextView.text = context.getString(R.string.answer_wrongAnswer)
    }

    override fun tryAgain(): Observable<Unit> = RxView.clicks(tryAgainButton)

    override fun next(): Observable<Unit> = RxView.clicks(nextButton)

    override fun close() = dismiss()
}
