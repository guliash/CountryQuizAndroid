package com.guliash.countryquiz.quiz;

import java.util.List;

import rx.Observable;

public class GameImp implements Game {

    private Provider provider;
    private List<Quiz> quizzes;
    private int currentQuizIndex;

    public GameImp(Provider provider) {
        this.provider = provider;
    }

    @Override
    public Observable<Quiz> next() {
        return Observable.fromCallable(() -> {
            if(quizzes == null) {
                quizzes = provider.getQuizzesByCriteria(null);
            }
            Quiz quiz = quizzes.get(currentQuizIndex);
            currentQuizIndex = (currentQuizIndex + 1) % quizzes.size();
            return quiz;
        });
    }

    @Override
    public Observable<Quiz> get(String id) {
        return Observable.just(provider.getQuizById(id));
    }

    private Quiz currentQuiz() {
        return quizzes.get(currentQuizIndex);
    }

    @Override
    public boolean answer(String answer) {
        return currentQuiz().getAnswer().equals(answer);
    }
}
