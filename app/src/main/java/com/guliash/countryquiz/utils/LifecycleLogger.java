package com.guliash.countryquiz.utils;

import com.guliash.countryquiz.core.BaseDialogFragment;

import clojure.lang.Obj;
import timber.log.Timber;

public class LifecycleLogger {

    public static void create(Object object) {
        Timber.d("ON CREATE %s", object);
    }

    public static void destroy(Object object) {
        Timber.d("ON DESTROY %s", object);
    }

    public static void start(Object object) {
        Timber.d("ON START %s", object);
    }

    public static void stop(Object object) {
        Timber.d("ON STOP %s", object);
    }

    public static void resume(Object object) {
        Timber.d("ON RESUME %s", object);
    }

    public static void pause(Object object) {
        Timber.d("ON PAUSE %s", object);
    }

    public static void save(Object object) {
        Timber.d("ON SAVE %s", object);
    }

    public static void restore(Object object) {
        Timber.d("ON RESTORE %s", object);
    }

    public static void createView(Object object) {
        Timber.d("ON CREATE VIEW %s", object);
    }

    public static void destroyView(Object object) {
        Timber.d("DESTROY VIEW %s", object);
    }

    public static void viewCreated(Object object) {
        Timber.d("ON VIEW CREATED %s", object);
    }

    public static void attach(Object object) {
        Timber.d("ON ATTACH %s", object);
    }

    public static void detach(Object object) {
        Timber.d("ON DETACH %s", object);
    }

    public static void newIntent(Object object) {
        Timber.d("ON NEW INTENT %s", object);
    }

    public static void createDialog(Object object) {
        Timber.d("ON CREATE DIALOG %s", object);
    }
}
