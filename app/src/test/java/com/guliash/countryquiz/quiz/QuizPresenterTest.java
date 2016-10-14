package com.guliash.countryquiz.quiz;

import com.guliash.countryquiz.RxTester;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.emory.mathcs.backport.java.util.Arrays;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizPresenterTest extends RxTester {

    private static final Quiz[] QUIZZES = new Quiz[]{
            new Quiz("1", "Great Sphinx of Giza", "https://en.wikipedia.org/wiki/Great_Sphinx_of_Giza",
                    "statue", Arrays.asList(new String[]{"Egypt", "Russia", "USA", "China"}),
                    "Egypt",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Great_Sphinx_of_Giza_May_2015.JPG/1024px-Great_Sphinx_of_Giza_May_2015.JPG",
                    "By MusikAnimal - Own work, CC BY-SA 3.0, https://commons.wikimedia.org/w/index.php?curid=40954313")
    };

    private QuizPresenter presenter;

    @Mock
    QuizContract.View view;

    @Mock
    Game game;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        when(game.next()).thenReturn(Observable.from(QUIZZES));

        presenter = new QuizPresenter(game);
    }

    @Test
    public void attachView_normal_loadsGame() {
        presenter.attachView(view);

        verify(game).next();
        verify(view).showQuiz(QUIZZES[0]);
    }

    @Test
    public void check_normal_verifiesAnswer() {
        presenter.attachView(view);
        presenter.check(QUIZZES[0].getAnswer());

        verify(game).answer(QUIZZES[0].getAnswer());
    }
}