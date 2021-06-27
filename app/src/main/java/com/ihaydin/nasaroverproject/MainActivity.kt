package com.ihaydin.nasaroverproject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.ihaydin.nasaroverproject.base.BaseActivity
import com.ihaydin.nasaroverproject.databinding.ActivityMainBinding
import com.ihaydin.nasaroverproject.entity.DataResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private var mRoverName : String? = null
    private var mCameraName : String? = null
    private var page = 1

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoverName = Rover.CURIOSITY.name
        mCameraName = Camera.ALL.name

        tabRoverSelected()
        selectCamera()

    }

    private fun selectCamera() {
        dataBinding.spnCamera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, v: View?, p: Int, id: Long) {
                when(p){
                    0 ->{ mCameraName = Camera.ALL.name }
                    1 ->{ mCameraName = Camera.FHAZ.name }
                    2 ->{ mCameraName = Camera.RHAZ.name }
                    3 ->{ mCameraName = Camera.MAST.name }
                    4 ->{ mCameraName = Camera.CHEMCAM.name }
                    5 ->{ mCameraName = Camera.MAHLI.name }
                    6 ->{ mCameraName = Camera.MARDI.name }
                    7 ->{ mCameraName = Camera.NAVCAM.name }
                    8 ->{ mCameraName = Camera.PANCAM.name }
                    9 ->{ mCameraName = Camera.MINITES.name }
                }

                if (mCameraName == Camera.ALL.name){
                    allCameraPhotosRequest()
                } else {
                    selectedCameraPhotosRequest()
                }
            }
        }
    }

    private fun allCameraPhotosRequest(){
        viewModel.getAllCameraPhotos(mRoverName!!, 1)
        observeAllCameraPhotos()
    }

    private fun selectedCameraPhotosRequest(){
        viewModel.getPhotosSelectedCamera(mRoverName!!, page, mCameraName!!)
        observeSelectedCameraPhotos()
    }

    private fun observeAllCameraPhotos(){
        viewModel.mAllCameraPhotos.observe(this, Observer {
            it.let {
                dataBinding.rvPhotos.layoutManager = LinearLayoutManager(applicationContext)
                dataBinding.rvPhotos.adapter = PhotosAdapter(it.photos){
                }
            }
        })
        observeLoadingAndError()
    }

    private fun observeSelectedCameraPhotos(){
        viewModel.mPhotosSelectedCamera.observe(this, Observer {
            it.let {
                dataBinding.rvPhotos.layoutManager = LinearLayoutManager(applicationContext)
                dataBinding.rvPhotos.adapter = PhotosAdapter(it.photos){
                }
            }
        })
        observeLoadingAndError()
    }

    private fun observeLoadingAndError(){
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
                        mRoverName = Rover.CURIOSITY.name
                        if (mCameraName == Camera.ALL.name){
                            allCameraPhotosRequest()
                        } else {
                            selectedCameraPhotosRequest()
                        }
                    }
                    1 -> {
                        mRoverName = Rover.OPPORTUNITY.name
                        if (mCameraName == Camera.ALL.name){
                            allCameraPhotosRequest()
                        } else {
                            selectedCameraPhotosRequest()
                        }
                    }
                    2 -> {
                        mRoverName = Rover.SPIRIT.name
                        if (mCameraName == Camera.ALL.name){
                            allCameraPhotosRequest()
                        } else {
                            selectedCameraPhotosRequest()
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    enum class Camera {
        ALL, FHAZ, RHAZ, MAST, CHEMCAM, MAHLI, MARDI, NAVCAM, PANCAM, MINITES
    }

    enum class Rover {
        CURIOSITY, OPPORTUNITY, SPIRIT
    }

}