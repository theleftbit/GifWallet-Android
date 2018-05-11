package com.theleftbit.gifwallet.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.network.api.GPHApiClient
import com.theleftbit.gifwallet.R
import kotlinx.android.synthetic.main.fragment_gif_list.*

class GifListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        LayoutInflater.from(context).inflate(R.layout.fragment_gif_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = GifListAdapter()
        fragmentGifListRecyclerView.layoutManager = GridLayoutManager(context, 2)
        fragmentGifListRecyclerView.adapter = adapter
        val client = GPHApiClient("API-KEY")
        client.trending(MediaType.gif, null, null, null) { result, _ ->
            if (result != null && result.data != null) {
                result.data.forEach {
                    adapter.add(it.images.original.gifUrl)
                }
            }
        }
    }
}
