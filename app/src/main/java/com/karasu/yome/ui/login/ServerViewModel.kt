package com.karasu.yome.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServerViewModel : ViewModel() {

    private val address = MutableLiveData<String>()
    val serverAddress: LiveData<String> get() = address

    fun setServer(newAddress: String) {
        address.postValue(newAddress)
    }
}