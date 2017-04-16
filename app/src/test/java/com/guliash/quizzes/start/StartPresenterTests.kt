package com.guliash.quizzes.start

import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class StartPresenterTests {

    @Mock
    lateinit var view: StartView

    @Mock
    lateinit var actionsDelegate: ActionsDelegate

    val learns = PublishSubject.create<Any>()
    val plays = PublishSubject.create<Any>()

    lateinit var presenter: StartPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        `when`(view.learns()).thenReturn(learns)
        `when`(view.plays()).thenReturn(plays)

        presenter = StartPresenter(actionsDelegate)
    }

    @Test
    fun bindUnbind__withoutErrors() {
        presenter.bind(view)
        presenter.unbind()
    }

    @Test
    fun learn__navigatesToLearn() {
        presenter.bind(view)
        learns.onNext(Unit)
        presenter.unbind()

        verify(actionsDelegate).showLearn()
    }

    @Test
    fun play__navigatesToGame() {
        presenter.bind(view)
        plays.onNext(Unit)
        presenter.unbind()

        verify(actionsDelegate).showPlay()
    }

}
