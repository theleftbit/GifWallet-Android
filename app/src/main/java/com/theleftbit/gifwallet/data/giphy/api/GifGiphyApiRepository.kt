package com.theleftbit.gifwallet.data.giphy.api

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.theleftbit.gifwallet.data.giphy.mapper.GifModelMapper
import com.theleftbit.gifwallet.data.giphy.model.GifGiphyDataModel
import com.theleftbit.gifwallet.domain.model.GifModel
import com.theleftbit.gifwallet.domain.repository.GifRepository
import java.io.StringReader
import java.net.URL

class GifGiphyApiRepository: GifRepository {
    private val mapper: GifModelMapper = GifModelMapper()

    override fun getTrending(): List<GifModel> {
        val result = mutableListOf<GifModel>()
        val parsedResult = arrayListOf<GifGiphyDataModel?>()
        val spec = "http://api.giphy.com/v1/gifs/trending?api_key=kw7ABCKe5AfWxPu0qLcjaN7MpQdqAPES"
        val stringResult = URL(spec).readText()
        val klaxon = Klaxon()
        JsonReader(StringReader(stringResult)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val gif = klaxon.parse<GifGiphyDataModel>(reader)
                    parsedResult.add(gif)
                }
            }
        }
        parsedResult.forEach {
            it?.let {
                result.add(mapper.map(it))
            }
        }
        return result
    }
}
