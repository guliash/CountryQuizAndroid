package com.guliash.quizzes.game.questions.answer.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.text.method.LinkMovementMethod
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.guliash.quizzes.R
import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.ui.dialog.BaseDialogFragment
import com.guliash.quizzes.core.utils.collections.joinToString
import com.guliash.quizzes.core.view.CustomScrollView
import com.guliash.quizzes.game.questions.answer.AnswerUtils
import com.guliash.quizzes.game.questions.answer.di.AnswerModule
import com.guliash.quizzes.game.questions.answer.di.ComponentProvider
import com.guliash.quizzes.game.questions.answer.model.Answer
import com.guliash.quizzes.game.questions.answer.presenter.ActionsDelegate
import com.guliash.quizzes.game.questions.answer.presenter.AnswerPresenter
import com.guliash.quizzes.game.questions.question.model.Verdict
import com.guliash.quizzes.map.model.Position
import com.guliash.quizzes.map.view.MapActivity
import com.guliash.quizzes.map.view.POSITION_EXTRA
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import javax.inject.Inject

private val VERDICT_EXTRA = "verdict"
private val QUESTION_ID_EXTRA = "questionId"

fun createAnswerFragment(verdict: Verdict, questionId: String): DialogFragment {
    val args = Bundle()
    args.putParcelable(VERDICT_EXTRA, verdict)
    args.putString(QUESTION_ID_EXTRA, questionId)
    val fragment = AnswerFragment()
    fragment.arguments = args
    return fragment
}

class AnswerFragment : BaseDialogFragment(), AnswerView, ActionsDelegate {

    @BindView(R.id.title)
    lateinit var verdictTextView: TextView

    @BindView(R.id.description)
    lateinit var descriptionTextView: TextView

    @BindView(R.id.facts)
    lateinit var factsTextView: TextView

    @BindView(R.id.tryAgain)
    lateinit var tryAgainButton: Button

    @BindView(R.id.showOnMap)
    lateinit var showOnMapButton: Button

    @BindView(R.id.next)
    lateinit var nextButton: Button

    @BindView(R.id.scrollablePart)
    lateinit var scrollablePartView: CustomScrollView

    @BindView(R.id.topDivider)
    lateinit var topDivider: View

    @BindView(R.id.bottomDivider)
    lateinit var bottomDivider: View

    @Inject
    lateinit var presenter: AnswerPresenter

    @Inject
    lateinit var answerUtils: AnswerUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        (parentFragment as? ComponentProvider)?.create(
                AnswerModule(
                        arguments.getParcelable(VERDICT_EXTRA),
                        arguments.getString(QUESTION_ID_EXTRA),
                        this
                )
        )?.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.answer_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        scrollablePartView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                manageDividersState(scrollY)
            }
        })

        descriptionTextView.movementMethod = LinkMovementMethod.getInstance()

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
        dialog.window.setLayout((metrics.widthPixels * 0.9).toInt(), WRAP_CONTENT)
        scrollablePartView.maxHeight = (metrics.heightPixels * 0.6).toInt()
    }

    override fun showCorrectAnswer(answer: Answer) {
        tryAgainButton.visibility = GONE
        showOnMapButton.visibility = VISIBLE
        verdictTextView.setTextColor(ContextCompat.getColor(context, R.color.answer_correctAnswer))
        verdictTextView.text = context.getString(R.string.answer_correctAnswer)
    }

    override fun showWrongAnswer(answer: Answer) {
        tryAgainButton.visibility = VISIBLE
        showOnMapButton.visibility = GONE
        verdictTextView.setTextColor(ContextCompat.getColor(context, R.color.answer_wrongAnswer))
        verdictTextView.text = context.getString(R.string.answer_wrongAnswer)
    }

    override fun showPlace(place: Place) {
        descriptionTextView.visibility = VISIBLE
        descriptionTextView.text = answerUtils.buildDescription(place)
        showFacts(place.facts)

        scrollablePartView.post {
            topDivider.visibility = INVISIBLE
            if (scrollablePartView.getChildAt(0).height > scrollablePartView.height) {
                bottomDivider.visibility = VISIBLE
            } else {
                bottomDivider.visibility = INVISIBLE
            }
        }
    }

    override fun hidePlace() {
        descriptionTextView.visibility = GONE
        factsTextView.visibility = GONE
    }

    override fun showMap(position: Position) {
        val intent = Intent(context, MapActivity::class.java)
        intent.putExtra(POSITION_EXTRA, position)
        startActivity(intent)
    }

    private fun showFacts(facts: List<String>) {
        val factsMerged = facts.joinToString("\n", context.getString(R.string.answer_factBullet) + " ")
        factsTextView.text = factsMerged
        if (factsMerged.isBlank()) {
            factsTextView.visibility = GONE
        } else {
            factsTextView.visibility = VISIBLE
        }
    }

    private fun manageDividersState(scrollY: Int) {
        if (scrollY != 0) {
            topDivider.visibility = VISIBLE
        } else {
            topDivider.visibility = INVISIBLE
        }

        if (scrollY == (scrollablePartView.getChildAt(0).height - scrollablePartView.height)) {
            bottomDivider.visibility = INVISIBLE
        } else {
            bottomDivider.visibility = VISIBLE
        }
    }

    override fun tryAgain(): Observable<Any> = RxView.clicks(tryAgainButton)

    override fun next(): Observable<Any> = RxView.clicks(nextButton)

    override fun showOnMap(): Observable<Any> = RxView.clicks(showOnMapButton)

    override fun close() = dismiss()
}
