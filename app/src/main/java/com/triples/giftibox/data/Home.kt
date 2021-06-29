package com.triples.giftibox.data

data class Home(
    var subject: String = "분류 항목이 없습니다.",
    var couponList: ArrayList<Coupon> = arrayListOf()
)
