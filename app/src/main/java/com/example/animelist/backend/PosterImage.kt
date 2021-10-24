package com.example.animelist.backend

import com.google.gson.annotations.SerializedName

data class PosterImage (
    @SerializedName("original") val originalImage: String
        )