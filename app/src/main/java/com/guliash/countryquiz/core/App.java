package com.guliash.countryquiz.core;

import android.app.Application;
import android.content.Context;

import com.guliash.countryquiz.BuildConfig;

import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        setAppComponent(DaggerAppComponent.builder().appModule(new AppModule(this)).build());
    }

    public static App get(Context context) {
        return (App)context.getApplicationContext();
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
