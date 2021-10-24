package com.example.animelist.backend

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("canonicalTitle") val canonicalTitle: String,
    @SerializedName("averageRating") val averageRating: Double,
    @SerializedName("synopsis") val description: String,
    @Embedded  @SerializedName("posterImage") val posterImage: PosterImage,
    @Embedded @SerializedName("coverImage") val coverImage: CoverImage
    )