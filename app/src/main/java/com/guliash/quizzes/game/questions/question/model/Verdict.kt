package com.guliash.quizzes.game.questions.question.model

import android.os.Parcel
import android.os.Parcelable
import com.guliash.quizzes.game.questions.answer.model.Answer

class Verdict(val answer: Answer, val correct: Boolean) : Parcelable {

    companion object {
        val CREATOR: Parcelable.Creator<Verdict> = object : Parcelable.Creator<Verdict> {
            override fun createFromParcel(parcel: Parcel?): Verdict =
                    Verdict(parcel!!.readParcelable(Answer::class.java.classLoader),
                            parcel.readByte() == 1.toByte())

            override fun newArray(size: Int): Array<Verdict?> = arrayOfNulls(size)

        }
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeParcelable(answer, 0)
        parcel.writeByte(if (correct) 1 else 0)
    }

    override fun describeContents() = 0

}