package com.guliash.countryquiz.quiz.question.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.base.BaseActivity;
import com.guliash.countryquiz.core.base.BaseFragment;
import com.guliash.countryquiz.core.ui.ConfirmationDialog;
import com.guliash.countryquiz.quiz.answer.view.AnswerFragment;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.quiz.question.QuestionContract;
import com.guliash.countryquiz.quiz.question.presentation.QuestionPresenter;
import com.guliash.countryquiz.utils.Preconditions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionFragment extends BaseFragment implements QuestionContract.View,
        ConfirmationDialog.Provider, AnswerFragment.Provider {

    private static final String ANSWERS_HIDDEN_EXTRA = "collapse";
    private static final String CONFIRMATION_TAG = "confirmation";
    private static final String WRONG_ANSWER_TAG = "wrong";
    private static final String CORRECT_ANSWER_TAG = "correct";

    public interface Provider {
        Callbacks provideQuestionCallbacks();
    }

    public interface Callbacks {
        void onNextSelected(String tag);
    }

    @BindView(R.id.image)
    ImageView mImageView;

    @BindView(R.id.answers_container)
    ViewGroup mAnswersContainer;

    @BindView(R.id.toggle_answers)
    ImageView mToggleAnswersView;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @BindView(R.id.content)
    FrameLayout mContent;

    @Inject
    QuestionPresenter mPresenter;

    @BindViews({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    List<Button> mAnswerViews;

    private boolean mAreAnswersHidden;

    private BottomSheetBehavior mAnswersViewBehavior;

    private Callbacks mCallbacks;

    public static QuestionFragment newInstance() {
        Bundle bundle = new Bundle();

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);
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
        mCallbacks = getListener(Provider.class).provideQuestionCallbacks();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mAreAnswersHidden = true;
        } else {
            mAreAnswersHidden = savedInstanceState.getBoolean(ANSWERS_HIDDEN_EXTRA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupAnswersPanelListeners();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ANSWERS_HIDDEN_EXTRA, mAreAnswersHidden);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    private void setupAnswersPanelListeners() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAnswersContainer.getLayoutParams();
        mAnswersViewBehavior = (BottomSheetBehavior) params.getBehavior();

        Preconditions.checkNotNull(mAnswersViewBehavior);

        mAnswersViewBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    onAnswersHidden();
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    onAnswersShown();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        updateAnswersViewState();
        mPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @OnClick(R.id.toggle_answers)
    void onToggleAnswersViewClick() {
        if (mAreAnswersHidden) {
            showAnswersView();
        } else {
            hideAnswersView();
        }
    }

    private void showAnswersView() {
        mAnswersViewBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void hideAnswersView() {
        mAnswersViewBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void updateAnswersViewState() {
        if (mAreAnswersHidden) {
            onAnswersHidden();
        } else {
            onAnswersShown();
        }
    }

    private void onAnswersShown() {
        mAreAnswersHidden = false;
        mToggleAnswersView.setImageResource(R.drawable.ic_chevron_down_black_36dp);
    }

    private void onAnswersHidden() {
        mAreAnswersHidden = true;
        mToggleAnswersView.setImageResource(R.drawable.ic_chevron_up_black_36dp);
    }

    @OnClick({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    void onAnswerViewClick(View view) {
        selectAnswer(view);
    }

    private void selectAnswer(View view) {
        selectAnswer((String) view.getTag(R.id.answer_tag));
    }

    void selectAnswer(String answer) {
        mPresenter.onAnswerSelected(answer);
    }

    @Override
    public void showQuiz(Quiz quiz, Bitmap image) {
        showImage(image);
        showAnswers(quiz);
    }

    private void showImage(Bitmap image) {
        mImageView.setImageBitmap(image);
    }

    private void showAnswers(Quiz quiz) {
        Preconditions.equals(quiz.getAnswers().size(), mAnswerViews.size());
        for (int i = 0, size = mAnswerViews.size(); i < size; i++) {
            Button button = mAnswerViews.get(i);
            String answer = quiz.getAnswers().get(i);
            button.setTag(R.id.answer_tag, answer);
            button.setText(answer);
        }
    }

    @Override
    public void showQuizLoading() {
        mImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mAnswersContainer.setVisibility(View.GONE);

    }

    @Override
    public void hideQuizLoading() {
        mImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mAnswersContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConfirmation(String selectedAnswer) {
        ConfirmationDialog.newInstance(getString(R.string.confirmation_answer_dialog_title),
                getString(R.string.confirmation_text, selectedAnswer),
                getString(R.string.proceed), getString(R.string.cancel))
                .show(getChildFragmentManager(), CONFIRMATION_TAG);
    }

    @Override
    public void showCorrectAnswer(String quizId) {
        AnswerFragment.newInstance(quizId).show(getChildFragmentManager(), CORRECT_ANSWER_TAG);
    }

    @Override
    public void showWrongAnswer(String selectedAnswer) {
        ConfirmationDialog.newInstance(getString(R.string.wrong_answer),
                getString(R.string.wrong_answer_description, selectedAnswer),
                getString(R.string.try_again), null)
                .show(getChildFragmentManager(), WRONG_ANSWER_TAG);
    }

    @Override
    public ConfirmationDialog.Callbacks provideConfirmationCallbacks() {
        return mConfirmationCallbacks;
    }

    @Override
    public AnswerFragment.Callbacks provideAnswerCallbacks() {
        return mAnswerFragmentCallbacks;
    }

    private ConfirmationDialog.Callbacks mConfirmationCallbacks = new ConfirmationDialog.Callbacks() {
        @Override
        public void onConfirmed(String tag) {
            if(CONFIRMATION_TAG.equals(tag)) {
                mPresenter.onAnswerConfirmed();
            }
        }

        @Override
        public void onNotConfirmed(String tag) {
            if(CONFIRMATION_TAG.equals(tag)) {
                mPresenter.onAnswerNotConfirmed();
            }
        }
    };

    private AnswerFragment.Callbacks mAnswerFragmentCallbacks = new AnswerFragment.Callbacks() {
        @Override
        public void onNextSelected(String tag) {
            if(CORRECT_ANSWER_TAG.equals(tag)) {
                mCallbacks.onNextSelected(getTag());
            }
        }
    };
}
