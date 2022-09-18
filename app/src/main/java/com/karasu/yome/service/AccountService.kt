package com.karasu.yome.service

import com.karasu.yome.network.api.AccountApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountService @Inject constructor(private val accountApi: AccountApi) {

    suspend fun login(apiKey: String) {
        var user = accountApi.login(apiKey)
        UserService.setCurrentUser(user)
    }
}