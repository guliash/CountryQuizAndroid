package com.guliash.countryquiz.quiz.answer.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
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

    public interface Provider {
        Callbacks provideAnswerCallbacks();
    }

    public interface Callbacks {
        void onNextSelected(String tag);
    }

    @Inject
    AnswerPresenter mPresenter;

    @BindView(R.id.content_scroll)
    NestedScrollView mContentScrollView;

    @BindView(R.id.line_top)
    View mLineTop;

    @BindView(R.id.line_bottom)
    View mLineBottom;

    @BindView(R.id.description)
    TextView mDescription;

    private Callbacks mCallbacks;

    public static AnswerFragment newInstance(String quizId) {

        Bundle args = new Bundle();
        args.putString(QUIZ_ID_EXTRA, quizId);

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
    public void onAttach(Context context) {
        super.onAttach(context);

        mCallbacks = getListener(Provider.class).provideAnswerCallbacks();
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.answer_dialog, null, false);
        setupView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.correct_answer);
        builder.setPositiveButton(R.string.next, (dialog, which) -> mPresenter.nextSelected());
        builder.setNegativeButton(R.string.close, (dialog, which) -> {/*TODO*/});
        builder.setView(view);
        return builder.create();
    }

    public void setupView(View view) {
        ButterKnife.bind(this, view);

        mContentScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                changeVisibilityOfSeparators(scrollY);
            }
        });

        mContentScrollView.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                changeVisibilityOfSeparators(mContentScrollView.getScrollY()));
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);

        mPresenter.setData(getArguments().getString(QUIZ_ID_EXTRA));
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    private void changeVisibilityOfSeparators(int scroll) {
        mLineTop.setVisibility(scroll == 0 ? View.INVISIBLE : View.VISIBLE);
        mLineBottom.setVisibility(mContentScrollView.getChildAt(0).getMeasuredHeight() <=
                mContentScrollView.getMeasuredHeight() + scroll ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showQuiz(Quiz quiz) {
        mDescription.setText(quiz.getDescription());
    }

    @Override
    public void showNext() {
        mCallbacks.onNextSelected(getTag());
    }
}
