package com.guliash.quizzes.learn.details.presenter

import com.guliash.quizzes.core.app.di.rx.IO
import com.guliash.quizzes.core.app.di.rx.Main
import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.learn.details.DetailsScope
import com.guliash.quizzes.learn.details.MATERIAL_ID
import com.guliash.quizzes.learn.details.view.DetailsView
import com.guliash.quizzes.learn.service.MaterialsProvider
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@DetailsScope
class DetailsPresenter @Inject constructor(
        private val materialsProvider: MaterialsProvider,
        @Named(MATERIAL_ID) private val materialId: String,
        @IO private val workScheduler: Scheduler,
        @Main private val postScheduler: Scheduler
) :
        Presenter<DetailsView>() {

    override fun bind(view: DetailsView) {
        super.bind(view)

        materialsProvider.material(materialId)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler)
                .subscribe({ place -> view.showMaterial(place) })
    }

}