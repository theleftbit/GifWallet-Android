package com.theleftbit.gifwallet.data.giphy.model


data class ResultGifsGiphyDataModel(val data: List<GifGiphyDataModel>)

data class GifGiphyDataModel(val images: ImagesGiphyDataModel)

data class ImagesGiphyDataModel(val original: ImageGiphyDataModel, val downsized: ImageGiphyDataModel)

data class ImageGiphyDataModel(val url: String)
