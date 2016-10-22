package com.guliash.countryquiz.core;

import com.guliash.countryquiz.core.base.BaseNavigation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseNavigation mNavigation;

    public ActivityModule(BaseNavigation navigation) {
        mNavigation = navigation;
    }

    @Singleton
    @Provides
    public BaseNavigation provideNavigation() {
        return mNavigation;
    }
}
