package com.theleftbit.gifwallet.presentation.giflist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.theleftbit.gifwallet.data.giphy.api.GifGiphyApiRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

class GifListViewModel: ViewModel() {
    private val gifRepository = GifGiphyApiRepository()
    val urls = MutableLiveData<List<String>>()

    init {
        getTrending()
    }

    private fun getTrending() {
        async(CommonPool) {
            val gifs = gifRepository.getTrending()
            urls.postValue(gifs.map { it.sampleUrl })
        }
    }

    fun onSearchUpdated(query: String) {
        async(CommonPool) {
            val gifs = gifRepository.search(query)
            urls.postValue(gifs.map { it.sampleUrl })
        }
    }

    fun onSearchEmpty() {
        getTrending()
    }
}
