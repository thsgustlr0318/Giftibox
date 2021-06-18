package com.triples.giftibox.data

class Coupon {
    private var img: String
    private var brand: String
    private var menu: String
    private var date: String

    constructor(img: String, brand: String, menu: String, date: String){
        this.img = img
        this.brand = brand
        this.menu = menu
        this.date = date
    }

    fun getImg(): String?{
        return img;
    }

    fun setImg(img: String){
        this.img = img
    }

    fun getBrand(): String?{
        return brand
    }

    fun setBrand(brand: String){
        this.brand = brand
    }

    fun getMenu(): String?{
        return menu
    }

    fun setMenu(menu: String){
        this.menu = menu
    }

    fun getDate(): String?{
        return date
    }

    fun setDate(date: String){
        this.date = date
    }

}