package com.karasu.yome.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karasu.yome.model.User

class LoginViewModel : ViewModel() {

    private val usersList = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = usersList

    fun setUsers(newUsers: MutableList<User>) {
        usersList.value = newUsers
    }
}