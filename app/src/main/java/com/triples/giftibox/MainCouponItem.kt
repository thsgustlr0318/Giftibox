package com.triples.giftibox

class MainCouponItem {
    private var couponImg: String
    private var couponBrand: String
    private var couponMenu: String
    private var couponDate: String

    constructor(couponImg: String, couponBrand: String, couponMenu: String, couponDate: String){
        this.couponImg = couponImg
        this.couponBrand = couponBrand
        this.couponMenu = couponMenu
        this.couponDate = couponDate
    }

    fun getCouponImg(): String?{
        return couponImg;
    }

    fun setCouponImg(couponImg: String){
        this.couponImg = couponImg
    }

    fun getCouponBrand(): String?{
        return couponBrand;
    }

    fun setCouponBrand(couponBrand: String){
        this.couponBrand = couponBrand
    }

    fun getCouponMenu(): String?{
        return couponMenu;
    }

    fun setCouponMenu(couponMenu: String){
        this.couponMenu = couponMenu
    }

    fun getCouponDate(): String?{
        return couponDate;
    }

    fun setCouponDate(couponDate: String){
        this.couponDate = couponDate
    }

}