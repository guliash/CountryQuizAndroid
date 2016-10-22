package com.guliash.countryquiz.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guliash.countryquiz.core.ActivityComponent;
import com.guliash.countryquiz.utils.LifecycleLogger;

import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {

    protected ActivityComponent mComponent;

    protected void injectDependencies() {
        Timber.d("INJECT DEPENDENCIES %s", this);
    }

    public ActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LifecycleLogger.create(this);
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        LifecycleLogger.destroy(this);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        LifecycleLogger.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        LifecycleLogger.stop(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        LifecycleLogger.resume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        LifecycleLogger.pause(this);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LifecycleLogger.save(this);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LifecycleLogger.restore(this);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LifecycleLogger.newIntent(this);
        super.onNewIntent(intent);
    }
}
