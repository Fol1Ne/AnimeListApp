package com.example.animelist.backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object Server {
    private val api = Retrofit.Builder()
        .baseUrl("https://kitsu.io/api/edge/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

    suspend fun animeList(): List<Anime>{
        try {
            return api.animeList().data
        }catch (e: Exception){
            e.printStackTrace()
        }
        return emptyList()
    }
}