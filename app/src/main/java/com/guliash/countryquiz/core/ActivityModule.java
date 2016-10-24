package com.guliash.countryquiz.core;

import com.guliash.countryquiz.core.base.BaseNavigation;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseNavigation mNavigation;

    public ActivityModule(BaseNavigation navigation) {
        mNavigation = navigation;
    }

    @PerActivity
    @Provides
    public BaseNavigation provideNavigation() {
        return mNavigation;
    }

}
