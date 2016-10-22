package com.guliash.countryquiz.quiz.game;

import com.guliash.countryquiz.quiz.model.Quiz;

import rx.Observable;

public interface Game {

    Observable<Quiz> next();

    Observable<Quiz> get(String quizId);

    Observable<Boolean> answer(String quizId, String answer);

}
