package com.guliash.countryquiz.quiz;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.guliash.countryquiz.image.ImageManager;
import com.guliash.countryquiz.utils.Preconditions;
import com.guliash.countryquiz.utils.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import javax.inject.Inject;

import icepick.State;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuizPresenter extends QuizContract.Presenter {

    private Game game;

    private ImageManager imageManager;

    @State
    String quizId;

    @State
    String selectedAnswer;

    @Inject
    public QuizPresenter(Game game, ImageManager imageManager) {
        this.game = game;
        this.imageManager = imageManager;
    }

    @Override
    public void attachView(QuizContract.View view) {
        super.attachView(view);

        getView().showLoading();

        getGame();
    }

    private void getGame() {
        Observable<Quiz> quizObservable;
        if (StringUtils.isEmpty(quizId)) {
            quizObservable = game.next();
        } else {
            quizObservable = game.get(quizId);
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

        imageManager.loadImage(quiz.getImageUrl())
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
        if (!StringUtils.isEmpty(selectedAnswer)) {
            getView().selectAnswer(selectedAnswer);
        }
    }

    @Override
    void onCheck() {
        Preconditions.checkNotNull(selectedAnswer);

        game.answer(selectedAnswer);
    }

    @Override
    void onAnswerSelected(String answer) {
        selectedAnswer = answer;
    }

    @Override
    void onAnswerDeselected() {
        Preconditions.checkNotNull(selectedAnswer);

        selectedAnswer = null;
    }
}
