package com.ihaydin.nasaroverproject.entity


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("photos")
    val photos: List<Photo>
) {
    data class Photo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("sol")
        val sol: Int,
        @SerializedName("camera")
        val camera: Camera,
        @SerializedName("img_src")
        val imgSrc: String,
        @SerializedName("earth_date")
        val earthDate: String,
        @SerializedName("rover")
        val rover: Rover
    ) {
        data class Camera(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("rover_id")
            val roverId: Int,
            @SerializedName("full_name")
            val fullName: String
        )

        data class Rover(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("landing_date")
            val landingDate: String,
            @SerializedName("launch_date")
            val launchDate: String,
            @SerializedName("status")
            val status: String
        )
    }
}