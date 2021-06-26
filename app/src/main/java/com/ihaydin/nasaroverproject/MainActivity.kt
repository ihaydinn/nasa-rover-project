package com.ihaydin.nasaroverproject

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.ihaydin.nasaroverproject.base.BaseActivity
import com.ihaydin.nasaroverproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private var mRoverName : String? = null

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoverName = "curiosity"

        tabRoverSelected()

        makeRequest()
        observeLiveData()

    }

    private fun makeRequest(){
        viewModel.getAllCameraPhotos(mRoverName!!, 1)
    }

    private fun observeLiveData(){
        viewModel.mAllCameraPhotos.observe(this, Observer {
            it.let {
                dataBinding.rvPhotos.layoutManager = LinearLayoutManager(applicationContext)
                dataBinding.rvPhotos.adapter = PhotosAdapter(it.photos){
                }
            }
        })

        viewModel.mLoading.observe(this, Observer {
            if (it){
                dataBinding.pbLoading.visibility = View.VISIBLE
                dataBinding.rvPhotos.visibility = View.GONE
            } else {
                dataBinding.pbLoading.visibility = View.GONE
                dataBinding.rvPhotos.visibility = View.VISIBLE
            }
        })

        viewModel.mError.observe(this, Observer {
            if (it){
                dataBinding.ivError.visibility = View.VISIBLE
                dataBinding.tvError.visibility = View.VISIBLE
                dataBinding.pbLoading.visibility = View.GONE
                dataBinding.rvPhotos.visibility = View.GONE
            } else {
                dataBinding.ivError.visibility = View.GONE
                dataBinding.tvError.visibility = View.GONE
                dataBinding.pbLoading.visibility = View.VISIBLE
                dataBinding.rvPhotos.visibility = View.VISIBLE
            }
        })
    }

    private fun tabRoverSelected(){
        dataBinding.tabLayoutRover.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> {
                        mRoverName = "curiosity"
                        makeRequest()
                        observeLiveData()
                    }
                    1 -> {
                        mRoverName = "opportunity"
                        makeRequest()
                        observeLiveData()
                    }
                    2 -> {
                        mRoverName = "spirit"
                        makeRequest()
                        observeLiveData()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}