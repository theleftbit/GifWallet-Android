package com.theleftbit.gifwallet.presentation.giflist

import com.theleftbit.gifwallet.data.giphy.api.GifGiphyApiRepository
import com.theleftbit.gifwallet.domain.model.GifModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext

class GifListPresenter(var view: GifListView?) {

    private val gifRepository = GifGiphyApiRepository()

    fun onDestroyView() {
        view = null
    }

    fun onSearchUpdated(query: String) {
        launch(UI) {
            val result = withContext(CommonPool) { gifRepository.search(query) }
            updateGifList(result)
        }
    }

    fun onViewReady() {
        getTrending()
    }

    fun onSearchEmpty() {
        getTrending()
    }

    private fun getTrending() {
        launch(UI) {
            val result = withContext(CommonPool) { gifRepository.getTrending() }
            updateGifList(result)
        }
    }

    private fun updateGifList(gifs: List<GifModel>) {
        view?.updateGifList(gifs.map { it.sampleUrl })
    }
}
