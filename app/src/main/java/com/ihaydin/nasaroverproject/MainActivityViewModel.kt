package com.ihaydin.nasaroverproject

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ihaydin.nasaroverproject.base.BaseViewModel
import com.ihaydin.nasaroverproject.entity.DataResponse
import com.ihaydin.nasaroverproject.remote.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val mPhotosSelectedCamera = MutableLiveData<DataResponse>()
    val mAllCameraPhotos = MutableLiveData<DataResponse>()

    val mError = MutableLiveData<Boolean>()
    val mLoading = MutableLiveData<Boolean>()
    val mEmptyList = MutableLiveData<Boolean>()

    fun getPhotosSelectedCamera(roverName: String, page: Int, camera: String) {
        mLoading.value = true

        disposable.add(
            apiClient.getPhotosSelectedCamera(roverName, page, camera)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DataResponse>() {
                    override fun onSuccess(t: DataResponse) {
                        mPhotosSelectedCamera.value = t
                        mLoading.value = false
                        mEmptyList.value = t.photos.isEmpty()
                    }

                    override fun onError(e: Throwable) {
                        mLoading.value = false
                        mError.value = true
                    }
                })
        )
    }

    fun getAllCameraPhotos(roverName: String, page: Int) {
        mLoading.value = true

        disposable.add(
            apiClient.getAllCameraPhotos(roverName, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DataResponse>() {
                    override fun onSuccess(t: DataResponse) {
                        mAllCameraPhotos.value = t
                        mLoading.value = false
                        mEmptyList.value = t.photos.isEmpty()
                    }

                    override fun onError(e: Throwable) {
                        mLoading.value = false
                        mError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}