package com.theleftbit.gifwallet.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.network.api.GPHApiClient

class GifListViewModel: ViewModel() {

    val urls = MutableLiveData<List<String>>()

    init {
        val client = GPHApiClient("kw7ABCKe5AfWxPu0qLcjaN7MpQdqAPES")
        client.trending(MediaType.gif, null, null, null) { result, _ ->
            urls.postValue(result.data.map { it.images.original.gifUrl })
        }
    }
}
