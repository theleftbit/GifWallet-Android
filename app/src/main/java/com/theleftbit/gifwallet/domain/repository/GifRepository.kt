package com.theleftbit.gifwallet.domain.repository

import com.theleftbit.gifwallet.domain.model.GifModel

public interface GifRepository {
    fun getTrendy(): List<GifModel>
}
