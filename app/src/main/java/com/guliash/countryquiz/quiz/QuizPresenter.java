package com.guliash.countryquiz.quiz;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuizPresenter extends QuizContract.Presenter {

    private Game game;

    @Inject
    public QuizPresenter(Game game) {
        this.game = game;
    }

    @Override
    public void attachView(QuizContract.View view) {
        super.attachView(view);

        getGame();
    }

    private void getGame() {
        game.next()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendQuizToView);
    }

    private void sendQuizToView(Quiz quiz) {
        if(getView() != null) {
            getView().showQuiz(quiz);
        }
    }

    @Override
    void check(String answer) {
        game.answer(answer);
    }
}
