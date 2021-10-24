package com.example.animelist.backend

import retrofit2.http.GET

interface Api {
    @GET("anime")
    suspend fun animeList(): ResponceData
}