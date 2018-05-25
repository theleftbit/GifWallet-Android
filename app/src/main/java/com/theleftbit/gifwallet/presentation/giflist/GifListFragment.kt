package com.theleftbit.gifwallet.presentation.giflist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theleftbit.gifwallet.R
import kotlinx.android.synthetic.main.fragment_gif_list.*

class GifListFragment: Fragment() {
    private val adapter = GifListAdapter()
    private var model: GifListViewModel? = null

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
