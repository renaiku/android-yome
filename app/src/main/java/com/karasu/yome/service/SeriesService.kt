package com.karasu.yome.service

import com.karasu.yome.model.ReadStatus
import com.karasu.yome.model.SeriesFilter
import com.karasu.yome.network.api.SeriesApi
import com.karasu.yome.network.dto.SeriesDto
import com.karasu.yome.network.dto.VolumeDto
import com.karasu.yome.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesService @Inject constructor(private val seriesApi: SeriesApi)  {

    suspend fun getSeriesForLibrary(
        libraryId: Int,
        pageNumber: Int = 0,
        pageSize: Int = Constants.ITEMS_PER_PAGE,
        filter: SeriesFilter = createSeriesFilter(null)
    ): List<SeriesDto> {
        return seriesApi.getSeriesForLibrary(libraryId, pageNumber, pageSize, filter).body() ?: emptyList()
    }

    suspend fun getVolumes(seriesId: Int): List<VolumeDto> {
        return seriesApi.getVolumes(seriesId).body() ?: emptyList()
    }

    /**
     * Returns a default SeriesFilter if input param **filter** is null
     */
    private fun createSeriesFilter(filter: SeriesFilter?): SeriesFilter {
        if (filter != null) return filter;
        return SeriesFilter(
            arrayOf(),
            arrayOf(),
            ReadStatus(
                notRead = true,
                inProgress = true,
                read = true
            ),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            arrayOf(),
            0,
            arrayOf(),
            null,
            arrayOf(),
            arrayOf(),
            arrayOf(),
            ""
        )
    }
}