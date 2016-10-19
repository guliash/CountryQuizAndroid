package com.guliash.countryquiz.quiz;

import android.graphics.Bitmap;

import com.guliash.countryquiz.core.BasePresenter;
import com.guliash.countryquiz.core.BaseView;

public interface QuizContract {

    interface View extends BaseView {
        void showQuiz(Quiz quiz, Bitmap image);

        void showLoading();

        void hideLoading();

        void selectAnswer(String answer);

        void showRightGuessed();

        void showWrongGuessed();
    }

    abstract class Presenter extends BasePresenter<View> {
        abstract void onCheck();

        abstract void onAnswerSelected(String answer);

        abstract void onAnswerDeselected();

    }

}
