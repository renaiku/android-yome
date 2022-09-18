package com.karasu.yome.network.api

import com.karasu.yome.model.SeriesFilter
import com.karasu.yome.network.dto.SeriesDto
import com.karasu.yome.network.dto.VolumeDto
import retrofit2.Response
import retrofit2.http.*

interface SeriesApi {

    @POST("series")
    suspend fun getSeriesForLibrary(
        @Query("libraryId") libraryId: Int,
        @Query("PageNumber") pageNumber: Int,
        @Query("PageSize") pageSize: Int,
        @Body filter: SeriesFilter): Response<ArrayList<SeriesDto>>

    @GET("series/volumes")
    suspend fun getVolumes(
        @Query("seriesId") seriesId: Int
    ): Response<ArrayList<VolumeDto>>

}