package com.karasu.yome.service

import android.media.Image
import com.karasu.yome.network.api.ImageApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageService @Inject constructor(private val imageApi: ImageApi)  {

    suspend fun getSeriesCover(
        seriesId: Int
    ): Response<Image> {

        println("ImageService.getSeriesCover: $seriesId")
        return imageApi.getSeriesCover(seriesId)
    }
}