package com.ihaydin.nasaroverproject.remote

import com.ihaydin.nasaroverproject.Constant
import com.ihaydin.nasaroverproject.entity.DataResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(ApiService::class.java)

    fun getPhotosSelectedCamera(roverName: String, page: Int, camera: String): Single<DataResponse> {
        return api.getPhotosSelectedCamera(roverName, page, camera)
    }

    fun getAllCameraPhotos(roverName: String, page: Int): Single<DataResponse> {
        return api.getAllCameraPhotos(roverName, page)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())
        return client.build()
    }
}