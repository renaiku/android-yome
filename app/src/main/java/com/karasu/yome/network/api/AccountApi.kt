package com.karasu.yome.network.api

import com.karasu.yome.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AccountApi {

    @GET("account/roles")
    suspend fun getRoles(): Array<String>

    @POST("plugin/authenticate")
    suspend fun login(
        @Query("apiKey") apiKey: String
    ): User

}