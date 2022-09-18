package com.karasu.yome.network.api

import android.media.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ImageApi {

    @GET("image/series-cover")
    suspend fun getSeriesCover(
        @Query("seriesId") libraryId: Int
    ): Response<Image>

}