package com.theleftbit.gifwallet.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.theleftbit.gifwallet.R

class GifListAdapter: RecyclerView.Adapter<GifListAdapter.GifListItemViewHolder>() {
    private val items: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifListItemViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.item_gif_list, parent, false) as ImageView
        return GifListItemViewHolder(imageView)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: GifListItemViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
                .load(items[position])
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView)
    }

    fun add(gifUrl: String) {
        items.add(gifUrl)
        notifyItemInserted(items.size - 1)
    }

    class GifListItemViewHolder(val imageView: ImageView): RecyclerView.ViewHolder(imageView)
}
