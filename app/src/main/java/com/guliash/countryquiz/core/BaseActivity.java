package com.guliash.countryquiz.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guliash.countryquiz.utils.LifecycleLogger;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LifecycleLogger.create(this);
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
