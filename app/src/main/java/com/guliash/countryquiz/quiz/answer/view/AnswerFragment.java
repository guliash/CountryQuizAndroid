package com.guliash.countryquiz.quiz.answer.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.base.BaseActivity;
import com.guliash.countryquiz.core.base.BaseDialogFragment;
import com.guliash.countryquiz.quiz.answer.AnswerContract;
import com.guliash.countryquiz.quiz.answer.presentation.AnswerPresenter;
import com.guliash.countryquiz.quiz.model.Quiz;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerFragment extends BaseDialogFragment implements AnswerContract.View {

    static final String QUIZ_ID_EXTRA = "quiz_id";
    static final String SELECTED_ANSWER_EXTRA = "selected_answer";

    @Inject
    AnswerPresenter presenter;

    @BindView(R.id.title)
    TextView titleView;

    @BindView(R.id.dialog)
    ViewGroup dialog;

    @BindView(R.id.content_scroll)
    NestedScrollView contentScrollView;

    @BindView(R.id.line_top)
    View lineTop;

    @BindView(R.id.line_bottom)
    View lineBottom;

    @BindView(R.id.positive_button)
    Button positiveButton;

    @BindView(R.id.negative_button)
    Button negativeButton;

    @BindView(R.id.confirmation_text)
    TextView confirmationText;

    @BindView(R.id.confirmation_content)
    ViewGroup confirmationContent;

    @BindView(R.id.correct_content)
    ViewGroup correctAnswerContent;

    @BindView(R.id.wrong_content)
    ViewGroup wrongAnswerContent;

    public static AnswerFragment newInstance(String quizId, String selectedAnswer) {

        Bundle args = new Bundle();
        args.putString(QUIZ_ID_EXTRA, quizId);
        args.putString(SELECTED_ANSWER_EXTRA, selectedAnswer);

        AnswerFragment fragment = new AnswerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((BaseActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AnswerDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.answer_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        contentScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                changeVisibilityOfSeparators(scrollY);
            }
        });

        dialog.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                changeVisibilityOfSeparators(contentScrollView.getScrollY()));
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);

        presenter.setData(getArguments().getString(QUIZ_ID_EXTRA),
                getArguments().getString(SELECTED_ANSWER_EXTRA));
    }

    private void changeVisibilityOfSeparators(int scroll) {
        lineTop.setVisibility(scroll == 0 ? View.INVISIBLE : View.VISIBLE);
        lineBottom.setVisibility(contentScrollView.getChildAt(0).getMeasuredHeight() <=
                contentScrollView.getMeasuredHeight() + scroll ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showConfirmation(String answerToConfirm) {
        setTitle(getString(R.string.confirmation_answer_dialog_title));
        setNegativeText(getString(R.string.cancel));
        setPositiveText(getString(R.string.proceed));

        correctAnswerContent.setVisibility(View.GONE);
        wrongAnswerContent.setVisibility(View.GONE);
        confirmationContent.setVisibility(View.VISIBLE);

        confirmationText.setText(getString(R.string.confirmation_text, answerToConfirm));
    }

    private void setTitle(String title) {
        titleView.setText(title);
    }

    private void setPositiveText(String text) {
        positiveButton.setText(text);
    }

    private void setNegativeText(String text) {
        negativeButton.setText(text);
    }

    @Override
    public void showWrongAnswer(String wrongAnswer) {

    }

    @Override
    public void showCorrectAnswer(Quiz quiz) {

    }
}
