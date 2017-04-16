package com.guliash.quizzes.map.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.guliash.quizzes.R
import com.guliash.quizzes.core.QuizzesApplication
import com.guliash.quizzes.map.di.MapModule
import com.guliash.quizzes.map.model.Position
import com.guliash.quizzes.map.presenter.MapPresenter
import javax.inject.Inject

val POSITION_EXTRA = "position"

class MapActivity : AppCompatActivity(), MapView {

    lateinit var googleMap: GoogleMap
    lateinit var position: Position

    @Inject
    lateinit var mapPresenter: MapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        position = intent.getParcelableExtra(POSITION_EXTRA)

        QuizzesApplication.application(this)
                .appComponent
                .plus(MapModule(position))
                .inject(this)
    }

    override fun onStart() {
        super.onStart()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            this.googleMap = googleMap
            mapPresenter.bind(this)
        }
    }

    override fun onStop() {
        mapPresenter.unbind()
        super.onStop()
    }

    override fun showMarker(position: Position) {
        googleMap.addMarker(MarkerOptions().position(position.toLatLng()))
    }

    override fun setZoomControlsVisibility(visible: Boolean) {
        googleMap.uiSettings.isZoomControlsEnabled = visible
    }

    override fun moveCamera(position: Position, zoom: Float) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position.toLatLng(), 13f))
    }

    override fun setZoom(zoom: Float) {
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoom))
    }
}