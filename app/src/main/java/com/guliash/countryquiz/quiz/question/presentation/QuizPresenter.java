package com.guliash.countryquiz.quiz.question.presentation;

import android.graphics.Bitmap;

import com.guliash.countryquiz.core.base.BaseNavigation;
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

public class QuizPresenter extends QuestionContract.Presenter {

    private Game mGame;

    private ImageManager mImageManager;

    private QuestionContract.Navigation mNavigation;

    @State
    String quizId;

    @Inject
    public QuizPresenter(Game game, ImageManager imageManager, BaseNavigation navigation) {
        mGame = game;
        mImageManager = imageManager;
        mNavigation = Preconditions.checkType(navigation, QuestionContract.Navigation.class);
    }

    @Override
    public void attachView(QuestionContract.View view) {
        super.attachView(view);

        getView().showLoading();

        getGame();
    }

    private void getGame() {
        Observable<Quiz> quizObservable;
        if (StringUtils.isEmpty(quizId)) {
            quizObservable = mGame.next();
        } else {
            quizObservable = mGame.get(quizId);
        }
        quizObservable.
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQuizLoaded);
    }

    private void onQuizLoaded(Quiz quiz) {
        this.quizId = quiz.getId();
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
        getView().hideLoading();
        getView().showQuiz(quiz, image);
    }

    @Override
    public void onAnswerSelected(String selectedAnswer) {
        mNavigation.questionsAnswerSelected(quizId, selectedAnswer);
    }
}
