package com.guliash.quizzes.learn.preview.presenter

import com.guliash.quizzes.learn.preview.presenter.PreviewPresenter
import com.guliash.quizzes.learn.service.MaterialsProvider
import com.guliash.quizzes.learn.preview.view.PreviewView
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class PreviewPresenterTest {

    @Mock
    lateinit var materialsProvider: MaterialsProvider

    private val postScheduler = TestScheduler()
    private val workScheduler = TestScheduler()

    @Mock
    lateinit var view: PreviewView

    private lateinit var presenter: PreviewPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = PreviewPresenter(materialsProvider, 0, postScheduler, workScheduler)
    }

    @Test
    fun materialsProvider_error__doesNotFail() {
        `when`(materialsProvider.material(anyInt())).thenReturn(Single.error(Throwable()))

        presenter.bind(view)
        workScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        postScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        presenter.unbind()
    }

}