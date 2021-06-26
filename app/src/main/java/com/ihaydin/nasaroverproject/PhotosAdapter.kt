package com.ihaydin.nasaroverproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ihaydin.nasaroverproject.databinding.ItemRoverBinding
import com.ihaydin.nasaroverproject.entity.DataResponse

class PhotosAdapter(val photos: List<DataResponse.Photo>, val onItemClick: (DataResponse.Photo)->Unit): RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    class PhotoViewHolder(var view: ItemRoverBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(photo: DataResponse.Photo, onItemClick: (DataResponse.Photo) -> Unit){
            itemView.setOnClickListener { onItemClick(photo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRoverBinding>(inflate, R.layout.item_rover, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.view.photo = photos[position]
        holder.bind(photos[position], onItemClick)

    }

    override fun getItemCount(): Int = photos.size
}