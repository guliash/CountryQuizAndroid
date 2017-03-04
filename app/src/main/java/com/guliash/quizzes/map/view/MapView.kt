package com.guliash.quizzes.map.view

import com.guliash.quizzes.map.model.Position

interface MapView {
    fun showMarker(position: Position)

    fun setZoom(zoom: Float)

    fun moveCamera(position: Position, zoom: Float)

    fun setZoomControlsVisibility(visible: Boolean)
}