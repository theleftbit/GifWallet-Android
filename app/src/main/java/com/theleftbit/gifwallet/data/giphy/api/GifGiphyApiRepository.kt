package com.theleftbit.gifwallet.data.giphy.api

import com.google.gson.Gson
import com.theleftbit.gifwallet.data.giphy.mapper.GifModelMapper
import com.theleftbit.gifwallet.data.giphy.model.ResultGifsGiphyDataModel
import com.theleftbit.gifwallet.domain.model.GifModel
import com.theleftbit.gifwallet.domain.repository.GifRepository
import java.net.URL

class GifGiphyApiRepository: GifRepository {
    private val mapper: GifModelMapper = GifModelMapper()
    private val gson: Gson = Gson()

    override fun getTrending(): List<GifModel> {
        val spec = "http://api.giphy.com/v1/gifs/trending?api_key=kw7ABCKe5AfWxPu0qLcjaN7MpQdqAPES"
        val stringResult = URL(spec).readText()
        val gifs = gson.fromJson<ResultGifsGiphyDataModel>(stringResult, ResultGifsGiphyDataModel::class.java)
        return gifs.data.map { mapper.map(it) }
    }
}
