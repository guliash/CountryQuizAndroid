package com.guliash.countryquiz.quiz.game;

import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.quiz.provider.QuizProvider;

import java.util.List;

import rx.Observable;

public class GameImp implements Game {

    private QuizProvider quizProvider;
    private List<Quiz> quizzes;
    private int currentQuizIndex;

    public GameImp(QuizProvider quizProvider) {
        this.quizProvider = quizProvider;
    }

    @Override
    public Observable<Quiz> next() {
        return Observable.fromCallable(() -> {
            if(quizzes == null) {
                quizzes = quizProvider.getQuizzesByCriteria(null);
            }
            Quiz quiz = quizzes.get(currentQuizIndex);
            currentQuizIndex = (currentQuizIndex + 1) % quizzes.size();
            return quiz;
        });
    }

    @Override
    public Observable<Quiz> get(String id) {
        return Observable.just(quizProvider.getQuizById(id));
    }

    private Quiz currentQuiz() {
        return quizzes.get(currentQuizIndex);
    }

    @Override
    public boolean answer(String answer) {
        return currentQuiz().getAnswer().equals(answer);
    }
}
