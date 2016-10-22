package com.guliash.countryquiz.quiz.answer;

import com.guliash.countryquiz.core.base.BaseNavigation;
import com.guliash.countryquiz.core.base.BasePresenter;
import com.guliash.countryquiz.core.base.BaseView;
import com.guliash.countryquiz.quiz.model.Quiz;

public interface AnswerContract {

    interface View extends BaseView {
        void showConfirmation(String answerToConfirm);

        void showWrongAnswer(String wrongAnswer);

        void showCorrectAnswer(Quiz quiz);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void setData(String quizId, String selectedAnswer);

        public abstract void sureSelected();

        public abstract void notSureSelected();

        public abstract void tryAnotherSelected();

        public abstract void nextSelected();
    }

    interface Navigation extends BaseNavigation {
        void answersNotSureSelected();

        void answersTryAnotherSelected();

        void answersNextSelected();
    }

}
