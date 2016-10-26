package com.guliash.countryquiz.core.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.guliash.countryquiz.core.base.BaseDialogFragment;

public class ConfirmationDialog extends BaseDialogFragment {

    private static final String TITLE_EXTRA = "title";
    private static final String MESSAGE_EXTRA = "message";
    private static final String POSITIVE_EXTRA = "positive";
    private static final String NEGATIVE_EXTRA = "negative";

    public interface Provider {
        Callbacks provideConfirmationCallbacks();
    }

    public interface Callbacks {
        void onConfirmed(String tag);

        void onNotConfirmed(String tag);
    }

    private String mTitle;
    private String mMessage;
    private String mPositive;
    private String mNegative;

    private Callbacks mCallbacks;

    public static ConfirmationDialog newInstance(String title, String message, String positive,
                                                 String negative) {

        Bundle args = new Bundle();

        args.putString(TITLE_EXTRA, title);
        args.putString(MESSAGE_EXTRA, message);
        args.putString(POSITIVE_EXTRA, positive);
        args.putString(NEGATIVE_EXTRA, negative);

        ConfirmationDialog fragment = new ConfirmationDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = getListener(Provider.class).provideConfirmationCallbacks();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        mTitle = args.getString(TITLE_EXTRA);
        mMessage = args.getString(MESSAGE_EXTRA);
        mPositive = args.getString(POSITIVE_EXTRA);
        mNegative = args.getString(NEGATIVE_EXTRA);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle);
        }

        if(!TextUtils.isEmpty(MESSAGE_EXTRA)) {
            builder.setMessage(mMessage);
        }

        if(!TextUtils.isEmpty(POSITIVE_EXTRA)) {
            builder.setPositiveButton(mPositive, (dialog, which) -> {
                mCallbacks.onConfirmed(getTag());
            });
        }

        if (!TextUtils.isEmpty(NEGATIVE_EXTRA)) {
            builder.setNegativeButton(mNegative, (dialog, which) -> {
                mCallbacks.onNotConfirmed(getTag());
            });
        }

        return builder.create();
    }
}
