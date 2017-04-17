package com.guliash.quizzes.game.questions.answer.model

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

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeString(text)
        parcel.writeByte(if (correct) 1 else 0)
    }

    override fun describeContents(): Int = 0

}