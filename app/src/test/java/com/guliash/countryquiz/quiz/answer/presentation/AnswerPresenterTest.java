package com.guliash.countryquiz.quiz.answer.presentation;

import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.quiz.answer.AnswerContract;
import com.guliash.countryquiz.quiz.game.Game;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static com.guliash.countryquiz.data.StubQuizzes.QUIZ1;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerPresenterTest extends RxTester {

    @Mock
    Game game;

    @Mock
    AnswerContract.View view;

    private AnswerPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new AnswerPresenter(game);

        presenter.attachView(view);
    }

    @Test(expected = NullPointerException.class)
    public void setData_quizIdNull_throwsNPE() {
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.setData(null);
    }

    @Test
    public void setData_normal_setsQuizId() {
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.setData("quizId");

        Assert.assertEquals("quizId", presenter.mQuizId);
    }

    @Test
    public void setData_normal_showsCorrectAnswer() {
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.setData(QUIZ1.getId());

        verify(view).showQuiz(QUIZ1);
    }

    @Test
    public void setData_viewDetached_notCallsShowCorrectAnswer() {
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.detachView();
        presenter.setData(QUIZ1.getId());

        verify(view, never()).showQuiz(anyObject());
    }

    @Test
    public void nextSelected_normal_navigates() {
        presenter.nextSelected();

        verify(view).showNext();
    }

}
