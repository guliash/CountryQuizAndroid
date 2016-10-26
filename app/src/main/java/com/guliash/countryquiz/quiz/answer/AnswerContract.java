package com.guliash.countryquiz.quiz.answer;

import com.guliash.countryquiz.core.base.BasePresenter;
import com.guliash.countryquiz.core.base.BaseView;
import com.guliash.countryquiz.quiz.model.Quiz;

public interface AnswerContract {

    interface View extends BaseView {
        void showQuiz(Quiz quiz);

        void showNext();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void setData(String quizId);

        public abstract void nextSelected();
    }

}
