package com.guliash.countryquiz.quiz;

import com.guliash.countryquiz.BuildConfig;
import com.guliash.countryquiz.R;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FakeProviderTests {

    private FakeProvider fakeProvider;

    @Before
    public void setup() {
        fakeProvider = new FakeProvider(RuntimeEnvironment.application, R.raw.test);
    }

    @Test
    public void getQuizzesByCriteria_statueCriteria_returnsOnlyStatuesQuizzes() {
        List<Quiz> quizList = fakeProvider.getQuizzesByCriteria(new Provider.QuizCriteria("statue"));
        for(Quiz quiz : quizList) {
            Assert.assertEquals("statue", quiz.getType());
        }
    }

    @Test
    public void getQuizzesByCriteria_buildingCriteria_returnsOnlyBuildingQuizzes() {
        List<Quiz> quizList = fakeProvider.getQuizzesByCriteria(new Provider.QuizCriteria("building"));
        for(Quiz quiz : quizList) {
            Assert.assertEquals("building", quiz.getType());
        }
    }

}
