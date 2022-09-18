package com.karasu.yome.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karasu.yome.model.User

class AddUserViewModel : ViewModel() {

    private val mUser = MutableLiveData<User>()
    val user: LiveData<User> get() = mUser

    fun setUser(newUser: User) {
        mUser.postValue(newUser)
    }
}