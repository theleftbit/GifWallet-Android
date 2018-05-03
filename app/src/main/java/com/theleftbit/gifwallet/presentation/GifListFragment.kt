package com.theleftbit.gifwallet.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theleftbit.gifwallet.R
import kotlinx.android.synthetic.main.fragment_gif_list.*

class GifListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        LayoutInflater.from(context).inflate(R.layout.fragment_gif_list, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = GifListAdapter()
        fragmentGifListRecyclerView.layoutManager = GridLayoutManager(context, 2)
        fragmentGifListRecyclerView.adapter = adapter
        adapter.items = mutableListOf<String>().apply {
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
            add("https://media.giphy.com/media/xTiTnqUxyWbsAXq7Ju/giphy.gif")
        }
        adapter.notifyDataSetChanged()
    }
}