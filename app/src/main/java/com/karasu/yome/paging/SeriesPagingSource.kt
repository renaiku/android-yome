package com.karasu.yome.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karasu.yome.network.dto.SeriesDto
import com.karasu.yome.service.SeriesService
import com.karasu.yome.utils.Constants

class SeriesPagingSource(private val seriesService: SeriesService, private val libraryId: Int): PagingSource<Int, SeriesDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesDto> {
        val currentPage = params.key ?: 0
        return try {
            val seriesList = seriesService.getSeriesForLibrary(libraryId, currentPage, params.loadSize)

            val nextKey = if (seriesList.isEmpty()) {
                null
            } else {
                // initial load size = 3 * ITEMS_PER_PAGE
                // ensure we're not requesting duplicating items, at the 2nd request
                currentPage + (params.loadSize / Constants.ITEMS_PER_PAGE)
            }

            LoadResult.Page(
                data = seriesList,
                prevKey = if (currentPage == 0) null else -1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, SeriesDto>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}