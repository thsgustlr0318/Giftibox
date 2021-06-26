package com.triples.giftibox.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullCoupon (
    var img: String = "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg",
    var brand: String = "브랜드를 입력해주세요.",
    var menu: String = "메뉴를 입력해주세요.",
    var date: String = "유효기한을 입력해주세요.",
    var barcode: String = "바코드가 인식되지 않았습니다.",
    var category: String = "기타",
    var register: String = "오늘 날짜 넣기",
    var memo: String = ""
) : Parcelable