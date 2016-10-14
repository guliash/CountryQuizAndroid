package com.guliash.countryquiz.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("ON CREATE");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Timber.d("ON DESTROY");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Timber.d("ON START");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Timber.d("ON STOP");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Timber.d("ON RESUME");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Timber.d("ON PAUSE");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Timber.d("ON SAVE INSTANCE STATE");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Timber.d("ON RESTORE INSTANCE STATE");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Timber.d("ON NEW INTENT");
        super.onNewIntent(intent);
    }
}
