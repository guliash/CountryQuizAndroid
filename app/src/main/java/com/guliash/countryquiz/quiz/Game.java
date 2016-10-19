package com.guliash.countryquiz.quiz;

import rx.Observable;

public interface Game {

    Observable<Quiz> next();

    Observable<Quiz> get(String id);

    boolean answer(String answer);

}
