package com.guliash.countryquiz.quiz;

import com.guliash.countryquiz.BuildConfig;
import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.quiz.view.QuizFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class QuizFragmentTest extends RxTester {

    private QuizFragment quizFragment;


    @Before
    public void setup() {
        quizFragment = new QuizFragment();
        startFragment(quizFragment);
    }

    @Test
    public void showQuiz_normal_answersShown() {
    }

}
