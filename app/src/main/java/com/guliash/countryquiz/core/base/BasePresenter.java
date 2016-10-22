package com.guliash.countryquiz.core.base;

import android.os.Bundle;

import icepick.Icepick;

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        Icepick.saveInstanceState(this, bundle);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        Icepick.restoreInstanceState(this, bundle);
    }

    public T getView() {
        return view;
    }

    protected boolean isDetached() {
        return getView() == null;
    }
}
