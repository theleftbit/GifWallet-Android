package com.theleftbit.gifwallet.domain.repository

import com.theleftbit.gifwallet.domain.model.GifModel

interface GifRepository {
    fun getTrending(): List<GifModel>
    fun search(query: String): List<GifModel>
}
