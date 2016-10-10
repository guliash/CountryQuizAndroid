package com.guliash.countryquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class QuizFragment extends Fragment {

    private static final String TEXT = "text";
    private static final String COLOR = "color";
    private static final Random RND = new Random();

    private String text;
    private int color;

    @BindView(R.id.text)
    TextView textView;

    @BindView(R.id.container)
    FrameLayout container;

    public static QuizFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(TEXT, text);
        bundle.putInt(COLOR, Color.rgb(RND.nextInt(256), RND.nextInt(256), RND.nextInt(256)));

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString(TEXT);
        color = getArguments().getInt(COLOR);
        Timber.d("ON CREATE %d", color);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(text);
        container.setBackgroundColor(color);
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "ON DESTROY " + text);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e("TAG", "ON DESTROY VIEW");
        super.onDestroyView();
    }
}
