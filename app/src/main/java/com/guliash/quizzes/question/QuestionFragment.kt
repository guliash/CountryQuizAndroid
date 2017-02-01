package com.guliash.quizzes.question

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
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.GameImpl
import com.guliash.quizzes.question.model.Question
import io.reactivex.android.schedulers.AndroidSchedulers

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

    private val game: Game = GameImpl()

    @BindView(R.id.image)
    lateinit var questionImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whichQuestion = arguments.getInt(WHICH_QUESTION_ARG)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)
        game.question(whichQuestion)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(::println)
                .subscribe({ question -> showQuestion(question) })
    }

    fun showQuestion(question: Question) {
        Glide.with(this)
                .load(question.imageUrl)
                .centerCrop()
                .into(questionImageView)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.question, container, false);
    }

}