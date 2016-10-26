package com.guliash.countryquiz.quiz.question;

import android.graphics.Bitmap;

import com.guliash.countryquiz.core.base.BasePresenter;
import com.guliash.countryquiz.core.base.BaseView;
import com.guliash.countryquiz.quiz.model.Quiz;

public interface QuestionContract {

    interface View extends BaseView {
        void showQuiz(Quiz quiz, Bitmap image);

        void showQuizLoading();

        void hideQuizLoading();

        void showConfirmation(String selectedAnswer);

        void showCorrectAnswer(String quizId);

        void showWrongAnswer(String selectedAnswer);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void onAnswerSelected(String answer);

        public abstract void onAnswerConfirmed();

        public abstract void onAnswerNotConfirmed();
    }
}
