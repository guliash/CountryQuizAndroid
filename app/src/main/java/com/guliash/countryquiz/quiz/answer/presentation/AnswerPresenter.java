package com.guliash.countryquiz.quiz.answer.presentation;

import com.guliash.countryquiz.quiz.answer.AnswerContract;
import com.guliash.countryquiz.quiz.game.Game;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.utils.Preconditions;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AnswerPresenter extends AnswerContract.Presenter {

    String mQuizId;

    private Game mGame;

    @Inject
    public AnswerPresenter(Game game) {
        mGame = game;
    }

    @Override
    public void setData(String quizId) {
        Preconditions.checkNotNull(quizId);

        mQuizId = quizId;

        mGame.get(mQuizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showQuiz, error -> {
                    //TODO error handling
                });
    }

    @Override
    public void nextSelected() {
        getView().showNext();
    }

    private void showQuiz(Quiz quiz) {
        Preconditions.checkNotNull(quiz);

        if (!isDetached()) {
            getView().showQuiz(quiz);
        }
    }
}
