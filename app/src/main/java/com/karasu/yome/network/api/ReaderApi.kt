package com.karasu.yome.network.api

import com.karasu.yome.model.SeriesFilter
import com.karasu.yome.network.dto.SeriesDto
import retrofit2.Response
import retrofit2.http.*

interface ReaderApi {

    @POST("series")
    suspend fun getPage(
        @Query("libraryId") libraryId: Int,
        @Query("PageNumber") pageNumber: Int,
        @Query("PageSize") pageSize: Int,
        @Body filter: SeriesFilter): Response<ArrayList<SeriesDto>>

}