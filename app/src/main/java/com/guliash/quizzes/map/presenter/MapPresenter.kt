package com.guliash.quizzes.map.presenter

import com.guliash.quizzes.core.mvp.Presenter
import com.guliash.quizzes.map.di.MapScope
import com.guliash.quizzes.map.model.Position
import com.guliash.quizzes.map.view.MapView
import javax.inject.Inject

private val DEFAULT_ZOOM = 13f;

@MapScope
class MapPresenter @Inject constructor(private val position: Position) : Presenter<MapView>() {

    override fun bind(view: MapView) {
        super.bind(view)

        view.setZoomControlsVisibility(true)
        view.showMarker(position)
        view.moveCamera(position, DEFAULT_ZOOM)
    }

}