package com.guliash.countryquiz.quiz;

import android.graphics.Bitmap;

import com.guliash.countryquiz.core.BasePresenter;
import com.guliash.countryquiz.core.BaseView;
import com.guliash.countryquiz.quiz.model.Quiz;

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
        public abstract void onCheck();

        public abstract void onAnswerSelected(String answer);

        public abstract void onAnswerDeselected();

    }

}
