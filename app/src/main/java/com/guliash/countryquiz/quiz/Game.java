package com.guliash.countryquiz.quiz;

import rx.Observable;

public interface Game {

    Observable<Quiz> next();

    boolean answer(String answer);

}
