package com.ihaydin.nasaroverproject.entity


import android.os.Parcel
import android.os.Parcelable
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
        val imgSrc: String?,
        @SerializedName("earth_date")
        val earthDate: String?,
        @SerializedName("rover")
        val rover: Rover
    ) : Parcelable{
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(Thread.currentThread().contextClassLoader)!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Thread.currentThread().contextClassLoader)!!,
        )

        data class Camera(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String?,
            @SerializedName("rover_id")
            val roverId: Int,
            @SerializedName("full_name")
            val fullName: String?
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString()
            ) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeInt(roverId)
                parcel.writeString(fullName)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Camera> {
                override fun createFromParcel(parcel: Parcel): Camera {
                    return Camera(parcel)
                }

                override fun newArray(size: Int): Array<Camera?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class Rover(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String?,
            @SerializedName("landing_date")
            val landingDate: String?,
            @SerializedName("launch_date")
            val launchDate: String?,
            @SerializedName("status")
            val status: String?
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
            ) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(landingDate)
                parcel.writeString(launchDate)
                parcel.writeString(status)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Rover> {
                override fun createFromParcel(parcel: Parcel): Rover {
                    return Rover(parcel)
                }

                override fun newArray(size: Int): Array<Rover?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeInt(sol)
            parcel.writeParcelable(camera,0)
            parcel.writeString(imgSrc)
            parcel.writeString(earthDate)
            parcel.writeParcelable(rover,0)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Photo> {
            override fun createFromParcel(parcel: Parcel): Photo {
                return Photo(parcel)
            }

            override fun newArray(size: Int): Array<Photo?> {
                return arrayOfNulls(size)
            }
        }
    }
}