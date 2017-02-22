package com.guliash.quizzes.game.model

import android.os.Parcel
import android.os.Parcelable

class Enigma(val name: String, val href: String) : Parcelable {

    companion object {
        val CREATOR: Parcelable.Creator<Enigma> = object : Parcelable.Creator<Enigma> {
            override fun createFromParcel(parcel: Parcel?): Enigma =
                    Enigma(parcel!!.readString(), parcel.readString())

            override fun newArray(size: Int): Array<Enigma?> = arrayOfNulls(size)

        }
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeString(name)
        parcel.writeString(href)
    }

    override fun describeContents(): Int = 0
}