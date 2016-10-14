package com.guliash.countryquiz.quiz;

import com.guliash.countryquiz.core.BasePresenter;
import com.guliash.countryquiz.core.BaseView;

public interface QuizContract {

    interface View extends BaseView {
        void showQuiz(Quiz quiz);

        void showRightGuessed(String answer);

        void showWrongGuessed(String answer);
    }

    abstract class Presenter extends BasePresenter<View> {
        abstract void check(String answer);
    }

}
