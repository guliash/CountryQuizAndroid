package com.guliash.quizzes.answer.model

import android.os.Parcel
import android.os.Parcelable

class Answer(val text: String, val correct: Boolean) : Parcelable {

    companion object {
        val CREATOR: Parcelable.Creator<Answer> = object : Parcelable.Creator<Answer> {
            override fun createFromParcel(parcel: Parcel?): Answer =
                    Answer(parcel!!.readString(), parcel.readByte() == 1.toByte())

            override fun newArray(size: Int): Array<Answer?> = arrayOfNulls(size)

        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int = 0

}