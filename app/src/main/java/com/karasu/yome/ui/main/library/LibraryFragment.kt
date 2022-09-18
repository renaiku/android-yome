package com.karasu.yome.ui.main.library

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.karasu.yome.adapter.SeriesPagedAdapter
import com.karasu.yome.databinding.FragmentLibraryBinding
import com.karasu.yome.model.Library
import com.karasu.yome.network.dto.SeriesDto
import com.karasu.yome.service.SeriesService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LibraryFragment(private val seriesService: SeriesService, library: Library): Fragment() {

    private lateinit var mAdapter: SeriesPagedAdapter

    private var _binding: FragmentLibraryBinding? = null
    private val viewModel: LibraryViewModel by viewModels { LibraryViewModelFactory(seriesService, library) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(layoutInflater)
        mAdapter = SeriesPagedAdapter()
        mAdapter.onItemClick = { item ->

            Log.d("LibraryFragment", "CLICKED ITEM: ${item.name}")
            GlobalScope.launch {
                onSeriesClicked(item)
            }
        }

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }

        // loading Data
        lifecycleScope.launch {
            viewModel.listData.collect {
                mAdapter.submitData(it)
            }
        }

        return binding.root
    }

    private suspend fun onSeriesClicked(series: SeriesDto) {
        val volumes = seriesService.getVolumes(series.id)
        println(volumes)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}