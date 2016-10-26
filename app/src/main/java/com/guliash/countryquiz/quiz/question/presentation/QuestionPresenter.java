package com.guliash.countryquiz.quiz.question.presentation;

import android.graphics.Bitmap;

import com.guliash.countryquiz.quiz.game.Game;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.quiz.question.QuestionContract;
import com.guliash.countryquiz.utils.Preconditions;
import com.guliash.countryquiz.utils.StringUtils;
import com.guliash.countryquiz.utils.image.ImageManager;

import javax.inject.Inject;

import icepick.State;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuestionPresenter extends QuestionContract.Presenter {

    private Game mGame;

    private ImageManager mImageManager;

    @State
    String mQuizId;

    @State
    String mSelectedAnswer;

    @Inject
    public QuestionPresenter(Game game, ImageManager imageManager) {
        mGame = game;
        mImageManager = imageManager;
    }

    @Override
    public void attachView(QuestionContract.View view) {
        super.attachView(view);

        getView().showQuizLoading();

        getGame();
    }

    private void getGame() {
        Observable<Quiz> quizObservable;
        if (StringUtils.isEmpty(mQuizId)) {
            quizObservable = mGame.next();
        } else {
            quizObservable = mGame.get(mQuizId);
        }
        quizObservable.
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQuizLoaded);
    }

    private void onQuizLoaded(Quiz quiz) {
        this.mQuizId = quiz.getId();
        loadImage(quiz);
    }

    private void loadImage(Quiz quiz) {
        Preconditions.checkNotNull(quiz);

        mImageManager.loadImage(quiz.getImageUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(image -> {
                    sendQuizToView(quiz, image);
                }, error -> {

                });
    }

    private void sendQuizToView(Quiz quiz, Bitmap image) {
        if (isDetached()) {
            return;
        }
        getView().hideQuizLoading();
        getView().showQuiz(quiz, image);
    }

    @Override
    public void onAnswerSelected(String selectedAnswer) {
        if (!isDetached()) {
            mSelectedAnswer = selectedAnswer;
            getView().showConfirmation(selectedAnswer);
        }
    }

    @Override
    public void onAnswerConfirmed() {
        mGame.answer(mQuizId, mSelectedAnswer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(result) {
                        sendCorrectAnswer();
                    } else {
                        sendWrongAnswer();
                    }
                }, error -> {
                    //TODO error handling
                });
    }

    private void sendCorrectAnswer() {
        if (!isDetached()) {
            getView().showCorrectAnswer(mQuizId);
        }
    }

    private void sendWrongAnswer() {
        if (!isDetached()) {
            getView().showWrongAnswer(mSelectedAnswer);
        }
    }

    @Override
    public void onAnswerNotConfirmed() {

    }
}