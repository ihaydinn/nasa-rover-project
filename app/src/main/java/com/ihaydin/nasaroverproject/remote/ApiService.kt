package com.ihaydin.nasaroverproject.remote

import com.ihaydin.nasaroverproject.entity.DataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("rovers/{roverName}/photos?sol=1000")
    fun getPhotosSelectedCamera(
        @Path("roverName") roverName: String,
        @Query("page") page: Int,
        @Query("camera") camera: String
    ): Single<DataResponse>

    @GET("rovers/{roverName}/photos?sol=1000")
    fun getAllCameraPhotos(
        @Path("roverName") roverName : String,
        @Query("page") page : Int
    ): Single<DataResponse>

}