package com.example.innoctive_test.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.innoctive_test.R
import com.example.innoctive_test.databinding.ListImagesBinding
import com.example.innoctive_test.view.onPageListener
import com.example.innoctive_test.viewmodel.model.DefaultModel

class ImageAdapter(pageListener: onPageListener) : RecyclerView.Adapter<MainViewHolder>() {
    var images = mutableListOf<DefaultModel>()
    var pagination: onPageListener = pageListener
    fun setImageList(movies: List<DefaultModel>) {
        this.images.addAll(movies)
        notifyDataSetChanged()
    }
    fun clearData() {
        this.images.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListImagesBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.itemView.context).load(image.url).placeholder(R.drawable.placeholder)
            .override(image.width, image.height)
            .into(holder.binding.image)

        if (position == (images.size -1)){
            pagination.onPage()
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }
}

class MainViewHolder(val binding: ListImagesBinding) : RecyclerView.ViewHolder(binding.root) {
}