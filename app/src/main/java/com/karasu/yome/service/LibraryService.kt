package com.karasu.yome.service

import com.karasu.yome.model.Library
import com.karasu.yome.network.api.LibraryApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryService @Inject constructor(private val libraryApi: LibraryApi)  {

    private var libraries: List<Library> = emptyList()

    fun getLibraries(): List<Library> {
        return libraries
    }

    suspend fun getLibrariesForMember(): List<Library> {
        libraries = libraryApi.getLibrariesForMember().body() ?: emptyList()
        return libraries
    }
}