package me.vponomarenko.letmethink.feature.search.viewdata

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: Valery Ponomarenko
 * Date: 25/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

data class SearchViewTransitionData(val revealStartX: Int,
                                    val revealStartY: Int): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(revealStartX)
        parcel.writeInt(revealStartY)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchViewTransitionData> {
        override fun createFromParcel(parcel: Parcel): SearchViewTransitionData {
            return SearchViewTransitionData(parcel)
        }

        override fun newArray(size: Int): Array<SearchViewTransitionData?> {
            return arrayOfNulls(size)
        }
    }

}