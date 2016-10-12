package com.guliash.countryquiz.di;

import android.content.Context;

import com.guliash.countryquiz.App;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.provider.FakeProvider;
import com.guliash.countryquiz.quiz.provider.Provider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeAppModule {

    private App app;

    public FakeAppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public Provider provideQuizzesProvider(Context context) {
        return new FakeProvider(context, R.raw.test);
    }

}
