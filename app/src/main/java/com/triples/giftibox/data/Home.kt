package com.triples.giftibox.data

class Home {

    private var subject: String
    private var couponList: ArrayList<Coupon>

    constructor(subject: String, couponList: ArrayList<Coupon>){
        this.subject = subject
        this.couponList = couponList
    }

    fun getSubject(): String{
        return subject
    }

    fun setSubject(subject:String){
        this.subject = subject
    }

    fun getCouponList(): ArrayList<Coupon> {
        return couponList
    }

    fun setCouponList(couponList: ArrayList<Coupon>){
        this.couponList = couponList
    }

}