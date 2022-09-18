package com.karasu.yome.service

object ServerService {

    private var url: String = "http://no.address.defined"

    fun getUrl(): String {
        println("ASKING FOR URL: $url")
        return url
    }

    fun setUrl(new: String) {
        url = new
        println("URL SET AS: $url")
    }

}