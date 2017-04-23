package com.guliash.quizzes.learn.presenter

import com.guliash.quizzes.core.app.di.rx.IO
import com.guliash.quizzes.core.app.di.rx.Main
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.learn.di.WhichMaterial
import com.guliash.quizzes.learn.service.MaterialsProvider
import com.guliash.quizzes.learn.view.MaterialView
import io.reactivex.Scheduler
import timber.log.Timber
import javax.inject.Inject

class MaterialPresenter @Inject constructor(private val materialsProvider: MaterialsProvider,
                                            @WhichMaterial private val whichMaterial: Int,
                                            @Main private val postScheduler: Scheduler,
                                            @IO private val workScheduler: Scheduler) :
        Presenter<MaterialView>() {

    override fun bind(view: MaterialView) {
        super.bind(view)

        subscribe(
                materialsProvider.material(whichMaterial)
                        .subscribeOn(workScheduler)
                        .observeOn(postScheduler)
                        .subscribe(
                                { place -> view.showMaterial(place) },
                                { error -> Timber.d(error, "Error getting material") }
                        )
        )
    }

}