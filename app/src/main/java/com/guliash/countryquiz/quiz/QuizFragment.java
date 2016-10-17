package com.guliash.countryquiz.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.guliash.countryquiz.App;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.BaseFragment;
import com.guliash.countryquiz.utils.Preconditions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import timber.log.Timber;

public class QuizFragment extends BaseFragment implements QuizContract.View {

    private static final String ANSWERS_HIDDEN_EXTRA = "collapse";
    private static final String SELECTED_EXTRA = "selected";

    private static final int NOT_SELECTED = 0;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.answers_container)
    ViewGroup variantsContainer;

    @BindView(R.id.toggle_answers)
    ImageView toggleAnswersView;

    @Inject
    QuizPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @BindViews({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    List<Button> answers;
    //TODO Better create something like viewmodel

    private boolean areAnswersHidden;
    private BottomSheetBehavior answersViewBehavior;
    private int selectedAnswerId;

    public static QuizFragment newInstance() {
        Bundle bundle = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.get(getContext()).getAppComponent().inject(this);

        if (savedInstanceState == null) {
            areAnswersHidden = true;
            selectedAnswerId = NOT_SELECTED;
        } else {
            areAnswersHidden = savedInstanceState.getBoolean(ANSWERS_HIDDEN_EXTRA);
            selectedAnswerId = savedInstanceState.getInt(SELECTED_EXTRA);
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
        setupVariantsPanel();

        if (selectedAnswerId != NOT_SELECTED) {
            selectAnswer(selectedAnswerId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ANSWERS_HIDDEN_EXTRA, areAnswersHidden);
        outState.putInt(SELECTED_EXTRA, selectedAnswerId);
    }

    private void setupVariantsPanel() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) variantsContainer.getLayoutParams();
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

        updateAnswersViewState();
        Timber.e(selectedAnswerId + " " + areAnswersHidden);
        if (selectedAnswerId != NOT_SELECTED) {
            selectAnswer(selectedAnswerId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @OnClick(R.id.toggle_answers)
    void onToggleAnswers() {
        if (areAnswersHidden) {
            showAnswersView();
        } else {
            hideAnswersView();
        }
    }

    @OnLongClick({R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4})
    boolean onAnswerLongClick(View view) {
        onAnswerSelectionEvent(view.getId());
        return true;
    }

    private void onAnswerSelectionEvent(int id) {
        final int prevAnswer = selectedAnswerId;
        if (prevAnswer == id) {
            deselectAnswer();
        } else if (prevAnswer == NOT_SELECTED) {
            selectAnswer(id);
        } else {
            deselectAnswer();
            selectAnswer(id);
        }

    }

    private void selectAnswer(int id) {
        selectedAnswerId = id;
        findAnswerView(selectedAnswerId).setSelected(true);
    }

    private void deselectAnswer() {
        Preconditions.notEquals(selectedAnswerId, NOT_SELECTED);
        findAnswerView(selectedAnswerId).setSelected(false);
        selectedAnswerId = NOT_SELECTED;
    }

    private View findAnswerView(int id) {
        Preconditions.notEquals(id, NOT_SELECTED);

        for (View view : answers) {
            if (view.getId() == id) {
                return view;
            }
        }

        throw new AssertionError("View not found");
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

    @Override
    public void showQuiz(Quiz quiz) {
        Timber.d("SHOW QUIZ");
        loadImage(quiz);
        showAnswers(quiz);
    }

    private void loadImage(Quiz quiz) {
        imageLoader.displayImage(quiz.getImageUrl(), image);
    }

    private void showAnswers(Quiz quiz) {
        Preconditions.equals(quiz.getAnswers().size(), answers.size());
        for (int i = 0, size = answers.size(); i < size; i++) {
            answers.get(i).setText(quiz.getAnswers().get(i));
        }
    }

    @Override
    public void showRightGuessed(String answer) {

    }

    @Override
    public void showWrongGuessed(String answer) {

    }
}
