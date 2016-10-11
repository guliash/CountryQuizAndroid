package com.guliash.countryquiz.di;

import com.guliash.countryquiz.App;

public class AppFactory {

    public static AppComponent createAppComponent(App app) {
        return DaggerFakeAppComponent.builder().fakeAppModule(new FakeAppModule(app)).build();
    }

}
