package com.guliash.countryquiz.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guliash.countryquiz.utils.LifecycleLogger;

import timber.log.Timber;

public class BaseFragment extends Fragment {

    protected void injectDependencies() {
        Timber.d("INJECT DEPENDENCIES %s", this);
    }

    @Override
    public void onAttach(Context context) {
        LifecycleLogger.attach(this);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        LifecycleLogger.detach(this);
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LifecycleLogger.create(this);
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        LifecycleLogger.destroy(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LifecycleLogger.createView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        LifecycleLogger.destroyView(this);
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        LifecycleLogger.start(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        LifecycleLogger.stop(this);
        super.onStop();
    }

    @Override
    public void onResume() {
        LifecycleLogger.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        LifecycleLogger.pause(this);
        super.onPause();
    }
}
