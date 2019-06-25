package com.arsoft.test.models

import android.os.Parcel
import android.os.Parcelable

class NewsCommentModel(
    var name: String,
    var surname: String,
    var avatar: String,
    var commentTime: String,
    var commentText: String,
    var likesCount: Int,
    var isLiked: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(commentTime)
        parcel.writeString(commentText)
        parcel.writeInt(likesCount)
        parcel.writeByte(if (isLiked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsCommentModel> {
        override fun createFromParcel(parcel: Parcel): NewsCommentModel {
            return NewsCommentModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsCommentModel?> {
            return arrayOfNulls(size)
        }
    }
}
