package com.guliash.countryquiz;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.guliash.countryquiz.core.BaseActivity;
import com.guliash.countryquiz.quiz.Provider;
import com.guliash.countryquiz.quiz.QuizAdapter;
import com.guliash.countryquiz.quiz.QuizPagerTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @BindView(R.id.quiz_pager)
    ViewPager viewPager;

    @Inject
    Provider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        App.get(this).getAppComponent().inject(this);

        Timber.e("PROVIDER %s", provider);

        viewPager.setAdapter(new QuizAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true, new QuizPagerTransformer());
    }

}
