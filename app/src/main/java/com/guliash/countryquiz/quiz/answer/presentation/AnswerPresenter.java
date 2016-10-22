package com.guliash.countryquiz.quiz.answer.presentation;

import com.guliash.countryquiz.core.base.BaseNavigation;
import com.guliash.countryquiz.quiz.answer.AnswerContract;
import com.guliash.countryquiz.quiz.game.Game;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.utils.Preconditions;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AnswerPresenter extends AnswerContract.Presenter {

    String mQuizId;
    String mSelectedAnswer;

    private Game mGame;
    private AnswerContract.Navigation mNavigation;

    @Inject
    public AnswerPresenter(Game game, BaseNavigation navigation) {
        mGame = game;
        mNavigation = Preconditions.checkType(navigation, AnswerContract.Navigation.class);
    }

    @Override
    public void setData(String quizId, String selectedAnswer) {
        Preconditions.checkNotNull(quizId);
        Preconditions.checkNotNull(selectedAnswer);

        mQuizId = quizId;
        mSelectedAnswer = selectedAnswer;

        showConfirmation();
    }

    @Override
    public void sureSelected() {
        Preconditions.checkNotNull(mQuizId);
        Preconditions.checkNotNull(mSelectedAnswer);

        mGame.answer(mQuizId, mSelectedAnswer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result) {
                        mGame.get(mQuizId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::sendCorrectAnswer, error -> {
                                    //TODO error handling
                                });
                    } else {
                        sendWrongAnswer();
                    }
                }, error -> {
                    //TODO error handling
                });
    }

    @Override
    public void notSureSelected() {
        mNavigation.answersNotSureSelected();
    }

    @Override
    public void tryAnotherSelected() {
        mNavigation.answersTryAnotherSelected();
    }

    @Override
    public void nextSelected() {
        mNavigation.answersNextSelected();
    }

    private void sendCorrectAnswer(Quiz quiz) {
        Preconditions.checkNotNull(quiz);

        if (!isDetached()) {
            getView().showCorrectAnswer(quiz);
        }
    }

    private void sendWrongAnswer() {
        Preconditions.checkNotNull(mSelectedAnswer);

        if (!isDetached()) {
            getView().showWrongAnswer(mSelectedAnswer);
        }
    }

    private void showConfirmation() {
        if(!isDetached()) {
            getView().showConfirmation(mSelectedAnswer);
        }
    }
}
