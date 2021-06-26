package com.ihaydin.nasaroverproject.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String){
    Glide.with(imageView.context).load(url).into(imageView)
}