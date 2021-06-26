package com.ihaydin.nasaroverproject

import android.app.Application
import androidx.lifecycle.MutableLiveData
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
    val mError = MutableLiveData<Boolean>()
    val mLoading = MutableLiveData<Boolean>()

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