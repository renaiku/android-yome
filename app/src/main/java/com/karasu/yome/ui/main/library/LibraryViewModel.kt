package com.karasu.yome.ui.main.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.karasu.yome.model.Library
import com.karasu.yome.service.SeriesService
import com.karasu.yome.paging.SeriesPagingSource
import com.karasu.yome.utils.Constants

class LibraryViewModelFactory(private val seriesService: SeriesService, private val library: Library): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LibraryViewModel(seriesService, library) as T
}

class LibraryViewModel(private val seriesService: SeriesService, private val library: Library) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = Constants.ITEMS_PER_PAGE)) {
        SeriesPagingSource(seriesService, library.id)
    }.flow.cachedIn(viewModelScope)

}