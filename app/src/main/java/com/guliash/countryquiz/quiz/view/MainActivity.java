package com.guliash.countryquiz.quiz.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.App;
import com.guliash.countryquiz.core.BaseActivity;
import com.guliash.countryquiz.quiz.provider.QuizProvider;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @BindView(R.id.quiz_pager)
    ViewPager viewPager;

    @Inject
    QuizProvider quizProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        App.get(this).getAppComponent().inject(this);

        Timber.e("PROVIDER %s", quizProvider);

        viewPager.setAdapter(new QuizAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true, new QuizPagerTransformer());

    }

}
