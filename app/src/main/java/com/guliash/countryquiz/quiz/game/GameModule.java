package com.guliash.countryquiz.quiz.game;

import com.guliash.countryquiz.quiz.provider.QuizProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GameModule {

    @Singleton
    @Provides
    public Game provideGame(QuizProvider provider) {
        return new GameImp(provider);
    }

}
