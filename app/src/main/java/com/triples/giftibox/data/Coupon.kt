package com.triples.giftibox.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "coupon")
// have to create primary key
data class Coupon (
    @PrimaryKey var menu: String = "메뉴를 입력해주세요.",
    @ColumnInfo(name = "img") var img: String = "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg",
    @ColumnInfo(name = "barcode") var barcode: String = "바코드가 인식되지 않았습니다.",
    @ColumnInfo(name = "brand") var brand: String = "브랜드를 입력해주세요.",
    @ColumnInfo(name = "date") var date: String = "유효기한을 입력해주세요.",
    @ColumnInfo(name = "category") var category: String = "기타",
    @ColumnInfo(name = "register") var register: String = "오늘 날짜 넣기",
    @ColumnInfo(name = "memo") var memo: String = ""
) : Parcelable