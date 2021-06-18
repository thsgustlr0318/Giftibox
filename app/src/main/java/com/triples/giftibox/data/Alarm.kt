package com.triples.giftibox.data

class Alarm {
    private var title: String
    private var content: String

    constructor(title: String, content: String){
        this.title = title
        this.content = content
    }

    fun getTitle(): String{
        return this.title
    }

    fun setTitle(title: String){
        this.title = title
    }
}