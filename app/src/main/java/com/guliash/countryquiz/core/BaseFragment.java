package com.guliash.countryquiz.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import timber.log.Timber;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.d("ON CREATE");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Timber.d("ON DESTROY");
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        Timber.d("ON ATTACH");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Timber.d("ON DETACH");
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.d("ON CREATE VIEW");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Timber.d("ON DESTROY VIEW");
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        Timber.d("ON START");
        super.onStart();
    }

    @Override
    public void onStop() {
        Timber.d("ON STOP");
        super.onStop();
    }

    @Override
    public void onResume() {
        Timber.d("ON RESUME");
        super.onResume();
    }

    @Override
    public void onPause() {
        Timber.d("ON PAUSE");
        super.onPause();
    }
}
