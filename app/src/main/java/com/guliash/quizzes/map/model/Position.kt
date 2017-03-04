package com.guliash.quizzes.map.model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class Position(val lat: Double, val lng: Double) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Position> = object : Parcelable.Creator<Position> {
            override fun newArray(size: Int): Array<Position?> = arrayOfNulls<Position>(size)

            override fun createFromParcel(source: Parcel?): Position {
                return Position(source!!.readDouble(), source.readDouble())
            }
        }
    }

    fun toLatLng() = LatLng(lat, lng)

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeDouble(lat)
        dest.writeDouble(lng)
    }

    override fun describeContents(): Int = 0
}