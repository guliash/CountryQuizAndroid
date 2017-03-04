package com.guliash.quizzes.map

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment
import com.guliash.quizzes.R

class MapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
        }
    }

}