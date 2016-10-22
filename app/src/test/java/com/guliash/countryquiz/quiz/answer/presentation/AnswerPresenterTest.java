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

    @Mock
    AnswerContract.Navigation navigation;

    private AnswerPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new AnswerPresenter(game, navigation);

        presenter.attachView(view);
    }

    @Test(expected = NullPointerException.class)
    public void setData_quizIdNull_throwsNPE() {
        presenter.setData(null, "test");
    }

    @Test(expected = NullPointerException.class)
    public void setData_answerNull_throwsNPE() {
        presenter.setData("test", null);
    }

    @Test
    public void setData_normal_setsQuizId() {
        presenter.setData("mQuizId", "answer");

        Assert.assertEquals("mQuizId", presenter.mQuizId);
    }

    @Test
    public void setData_normal_setsSelectedAnswer() {
        presenter.setData("mQuizId", "answer");

        Assert.assertEquals("answer", presenter.mSelectedAnswer);
    }

    @Test
    public void setData_normal_callsConfirmation() {
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());

        verify(view).showConfirmation(QUIZ1.getAnswer());
    }

    @Test(expected = NullPointerException.class)
    public void sureSelected_noQuizIdAndNoAnswer_throwsNPE() {
        presenter.mQuizId = null;
        presenter.mSelectedAnswer = null;

        presenter.sureSelected();
    }

    @Test(expected = NullPointerException.class)
    public void sureSelected_noAnswer_throwsNPE() {
        presenter.mQuizId = "quizId";
        presenter.mSelectedAnswer = null;

        presenter.sureSelected();
    }

    @Test(expected = NullPointerException.class)
    public void sureSelected_noQuizId_throwsNPE() {
        presenter.mQuizId = null;
        presenter.mSelectedAnswer = "answer";

        presenter.sureSelected();
    }

    @Test
    public void sureSelected_normal_checkAnswer() {
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(true));

        presenter.sureSelected();

        verify(game).answer(QUIZ1.getId(), QUIZ1.getAnswer());
    }

    @Test
    public void sureSelected_correctAnswer_showsCorrectAnswer() {
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(true));
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.sureSelected();

        verify(view).showCorrectAnswer(QUIZ1);
    }

    @Test
    public void sureSelected_viewDetached_notCallsShowCorrectAnswer() {
        presenter.detachView();
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(true));
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.sureSelected();

        verify(view, never()).showCorrectAnswer(anyObject());
    }

    @Test
    public void sureSelected_notCorrectAnswer_neverCallsShowCorrectAnswer() {
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(false));
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.sureSelected();

        verify(view, never()).showCorrectAnswer(QUIZ1);
    }

    @Test
    public void sureSelected_notCorrectAnswer_callsShowWrongAnswer() {
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(false));
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.sureSelected();

        verify(view).showWrongAnswer(QUIZ1.getAnswer());
    }

    @Test
    public void sureSelected_notCorrectAnswerAndViewDetached_notCallsShowWrongAnswer() {
        presenter.detachView();
        presenter.setData(QUIZ1.getId(), QUIZ1.getAnswer());

        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(false));
        when(game.get(anyString())).thenReturn(Observable.just(QUIZ1));

        presenter.sureSelected();

        verify(view, never()).showWrongAnswer(anyString());
    }

    @Test
    public void notSureSelected_normal_navigates() {
        presenter.notSureSelected();

        verify(navigation).answersNotSureSelected();
    }

    @Test
    public void tryAnotherSelected_normal_navigates() {
        presenter.tryAnotherSelected();

        verify(navigation).answersTryAnotherSelected();
    }

    @Test
    public void nextSelected_normal_navigates() {
        presenter.nextSelected();

        verify(navigation).answersNextSelected();
    }

}
