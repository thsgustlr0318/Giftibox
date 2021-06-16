package com.triples.giftibox.data

import android.os.Parcel
import android.os.Parcelable

class CouponParcel(var menu:String?, var img:String?, var barcode:String?, var brand:String?, var date:String?, var category:String?, var register: String?, var memo: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(menu)
        parcel.writeString(img)
        parcel.writeString(barcode)
        parcel.writeString(brand)
        parcel.writeString(date)
        parcel.writeString(category)
        parcel.writeString(register)
        parcel.writeString(memo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CouponParcel> {
        override fun createFromParcel(parcel: Parcel): CouponParcel {
            return CouponParcel(parcel)
        }

        override fun newArray(size: Int): Array<CouponParcel?> {
            return arrayOfNulls(size)
        }
    }
}