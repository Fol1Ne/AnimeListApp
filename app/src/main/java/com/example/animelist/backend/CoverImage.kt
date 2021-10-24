package com.example.animelist.backend

import com.google.gson.annotations.SerializedName

data class CoverImage (
    @SerializedName("original") val originalCoverImage: String
        )