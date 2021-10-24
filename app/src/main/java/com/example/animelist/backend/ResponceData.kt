package com.example.animelist.backend

import com.google.gson.annotations.SerializedName

data class ResponceData(
    @SerializedName("data") val data: List<Anime>,
)