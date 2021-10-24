package com.example.animelist.backend

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Anime(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @Embedded @SerializedName("attributes") val attributes: Attributes,
    @SerializedName("isWatching") var isWatching: Boolean = false,
    @SerializedName("isWantToWatch") var isWantToWatch: Boolean = false,
    @SerializedName("isWatched") var isWatched: Boolean = false
    )