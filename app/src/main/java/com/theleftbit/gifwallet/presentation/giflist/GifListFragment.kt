package com.theleftbit.gifwallet.presentation.giflist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theleftbit.gifwallet.R
import kotlinx.android.synthetic.main.fragment_gif_list.fragmentGifListRecyclerView
import kotlinx.android.synthetic.main.fragment_gif_list.searchCardView
import kotlinx.android.synthetic.main.fragment_gif_list.searchViewQuery

class GifListFragment : Fragment() {

    companion object {
        private const val MAX_Y_TRANSLATION = 0f
    }

    private val adapter = GifListAdapter()
    private var model: GifListViewModel? = null

    private val searchContainerHeight by lazy {
        (searchCardView.height.toFloat() + (searchCardView.layoutParams as ViewGroup.MarginLayoutParams).topMargin)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        LayoutInflater.from(context).inflate(R.layout.fragment_gif_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
        setUpSearchEditText()
    }

    private fun setUpRecyclerView() {
        fragmentGifListRecyclerView.layoutManager = GridLayoutManager(context, 2)
        fragmentGifListRecyclerView.adapter = adapter
        fragmentGifListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Prevents hiding when writing
                if (searchViewQuery.hasFocus()) {
                    return
                }
                val yPosition: Float = searchCardView.translationY
                val newYPosition = Math.min(Math.max(yPosition - dy.toFloat(), -searchContainerHeight), MAX_Y_TRANSLATION)
                if (newYPosition in -searchContainerHeight..MAX_Y_TRANSLATION) {
                    searchCardView.translationY = newYPosition
                }
            }
        })
    }

    private fun setUpViewModel() {
        model = ViewModelProviders.of(this).get(GifListViewModel::class.java)
        val observer = Observer<List<String>> {
            adapter.setAll(it)
        }
        model?.urls?.observe(this, observer)
    }

    private fun setUpSearchEditText() {
        searchViewQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    model?.onSearchEmpty()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                model?.onSearchUpdated(query.toString())
                return true
            }
        })
    }
}
