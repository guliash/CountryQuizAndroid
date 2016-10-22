package com.guliash.countryquiz.quiz.question.view;

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
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.quiz.question.QuestionContract;
import com.guliash.countryquiz.quiz.question.presentation.QuizPresenter;
import com.guliash.countryquiz.utils.Preconditions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionFragment extends BaseFragment implements QuestionContract.View {

    private static final String ANSWERS_HIDDEN_EXTRA = "collapse";

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.answers_container)
    ViewGroup answersContainer;

    @BindView(R.id.toggle_answers)
    ImageView toggleAnswersView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.content)
    FrameLayout content;

    @Inject
    QuizPresenter presenter;

    @BindViews({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    List<Button> answerViews;

    private boolean areAnswersHidden;

    private BottomSheetBehavior answersViewBehavior;

    public static QuestionFragment newInstance() {
        Bundle bundle = new Bundle();

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((BaseActivity)getActivity()).getComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            areAnswersHidden = true;
        } else {
            areAnswersHidden = savedInstanceState.getBoolean(ANSWERS_HIDDEN_EXTRA);
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
        outState.putBoolean(ANSWERS_HIDDEN_EXTRA, areAnswersHidden);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        presenter.onRestoreInstanceState(savedInstanceState);
    }

    private void setupAnswersPanelListeners() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) answersContainer.getLayoutParams();
        answersViewBehavior = (BottomSheetBehavior) params.getBehavior();

        Preconditions.checkNotNull(answersViewBehavior);

        answersViewBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
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
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @OnClick(R.id.toggle_answers)
    void onToggleAnswersViewClick() {
        if (areAnswersHidden) {
            showAnswersView();
        } else {
            hideAnswersView();
        }
    }

    private void showAnswersView() {
        answersViewBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void hideAnswersView() {
        answersViewBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void updateAnswersViewState() {
        if (areAnswersHidden) {
            onAnswersHidden();
        } else {
            onAnswersShown();
        }
    }

    private void onAnswersShown() {
        areAnswersHidden = false;
        toggleAnswersView.setImageResource(R.drawable.ic_chevron_down_black_36dp);
    }

    private void onAnswersHidden() {
        areAnswersHidden = true;
        toggleAnswersView.setImageResource(R.drawable.ic_chevron_up_black_36dp);
    }

    @OnClick({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    void onAnswerViewClick(View view) {
        selectAnswer(view);
    }

    private void selectAnswer(View view) {
        selectAnswer((String) view.getTag(R.id.answer_tag));
    }

    void selectAnswer(String answer) {
        presenter.onAnswerSelected(answer);
    }

    @Override
    public void showQuiz(Quiz quiz, Bitmap image) {
        showImage(image);
        showAnswers(quiz);
    }

    private void showImage(Bitmap image) {
        imageView.setImageBitmap(image);
    }

    private void showAnswers(Quiz quiz) {
        Preconditions.equals(quiz.getAnswers().size(), answerViews.size());
        for (int i = 0, size = answerViews.size(); i < size; i++) {
            Button button = answerViews.get(i);
            String answer = quiz.getAnswers().get(i);
            button.setTag(R.id.answer_tag, answer);
            button.setText(answer);
        }
    }

    @Override
    public void showLoading() {
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        answersContainer.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading() {
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        answersContainer.setVisibility(View.VISIBLE);
    }
}
