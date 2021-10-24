package com.example.animelist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.animelist.backend.Anime
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert
    suspend fun insert(anime: Anime)

    @Query("SELECT*FROM Anime")
    fun getAll(): Flow<List<Anime>>

    @Update
    suspend fun update(anime: Anime)
}