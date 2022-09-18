package com.karasu.yome.network.api

import com.karasu.yome.model.Library
import retrofit2.Response
import retrofit2.http.GET

interface LibraryApi {

    @GET("library")
    suspend fun getLibraries(): Array<Library>

    @GET("library/libraries")
    suspend fun getLibrariesForMember(): Response<List<Library>>

}