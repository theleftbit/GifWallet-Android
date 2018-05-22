package com.theleftbit.gifwallet.presentation.giflist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.network.api.GPHApiClient
import com.theleftbit.gifwallet.data.giphy.api.GifGiphyApiRepository

class GifListViewModel: ViewModel() {

    val urls = MutableLiveData<List<String>>()
    val gifRepository = GifGiphyApiRepository()

    init {
        val client = GPHApiClient("API-KEY")
        client.trending(MediaType.gif, null, null, null) { result, _ ->
            result?.data.let {
                urls.postValue(ArrayList<String>().apply {
                    result.data.forEach {
                        add(it.images.original.gifUrl)
                    }
                })
            }
        }
    }
}
