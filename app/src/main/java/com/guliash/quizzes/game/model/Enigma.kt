package com.guliash.quizzes.game.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Enigma(val name: String, val href: String, val facts: List<String>) : Parcelable {

    companion object {
        val CREATOR: Parcelable.Creator<Enigma> = object : Parcelable.Creator<Enigma> {
            override fun createFromParcel(parcel: Parcel?): Enigma {
                val name = parcel!!.readString()
                val href = parcel!!.readString()
                val facts = ArrayList<String>()
                parcel.readStringList(facts)
                return Enigma(name, href, facts)
            }

            override fun newArray(size: Int): Array<Enigma?> = arrayOfNulls(size)

        }
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeString(name)
        parcel.writeString(href)
        parcel.writeStringList(facts)
    }

    override fun describeContents(): Int = 0
}