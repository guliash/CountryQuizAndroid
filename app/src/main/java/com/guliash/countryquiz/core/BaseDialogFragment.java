package com.guliash.countryquiz.core;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guliash.countryquiz.utils.LifecycleLogger;

public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LifecycleLogger.createDialog(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        LifecycleLogger.destroy(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LifecycleLogger.createView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        LifecycleLogger.destroyView(this);
        super.onDestroyView();
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LifecycleLogger.createDialog(this);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LifecycleLogger.save(this);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        LifecycleLogger.restore(this);
        super.onViewStateRestored(savedInstanceState);
    }
}
