package com.triples.giftibox.data

class Alarm {
    private var icon: String = ""
    private var title: String
    private var content: String

    constructor(icon: String, title: String, content: String){
        this.icon = icon
        this.title = title
        this.content = content
    }

    constructor(title: String, content: String){
        this.title = title
        this.content = content
    }

    fun getIcon(): String{
        return this.icon
    }

    fun setIcon(icon: String){
        this.icon = icon
    }

    fun getTitle(): String{
        return this.title
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun getContent(): String{
        return this.content
    }

    fun setContent(content: String){
        this.content = content
    }


}