package com.theleftbit.gifwallet.data.giphy.mapper

import com.theleftbit.gifwallet.data.giphy.model.GifGiphyDataModel
import com.theleftbit.gifwallet.domain.model.GifModel

class GifModelMapper {

    fun map(source: GifGiphyDataModel): GifModel =
        GifModel(
            source.images.original.url,
            source.images.downsized.url)
}
