package com.guliash.countryquiz.quiz.game;

import com.guliash.countryquiz.quiz.models.Quiz;

import rx.Observable;

public interface Game {

    Observable<Quiz> next();

    boolean answer(String answer);

}
