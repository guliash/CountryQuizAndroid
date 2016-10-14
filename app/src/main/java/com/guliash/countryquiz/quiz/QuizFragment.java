package com.guliash.countryquiz.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.guliash.countryquiz.App;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class QuizFragment extends BaseFragment implements QuizContract.View {

    @BindView(R.id.image)
    ImageView image;

    @Inject
    QuizPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    public static QuizFragment newInstance() {
        Bundle bundle = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.get(getContext()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void showQuiz(Quiz quiz) {
        Timber.d("SHOW QUIZ");
        imageLoader.displayImage(quiz.getImageUrl(), image);
    }

    @Override
    public void showRightGuessed(String answer) {

    }

    @Override
    public void showWrongGuessed(String answer) {

    }
}
