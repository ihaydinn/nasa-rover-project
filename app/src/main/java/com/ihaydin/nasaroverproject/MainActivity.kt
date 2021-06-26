package com.ihaydin.nasaroverproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getPhotosSelectedCamera("curiosity", 1, "MAST")

        viewModel.mPhotosSelectedCamera.observe(this, Observer {
            it.let {
            }
        })

        viewModel.mError.observe(this, Observer {
            it.let {
            }
        })
    }
}