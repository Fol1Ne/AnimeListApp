package com.example.animelist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animelist.backend.Anime

@Database(entities = [Anime::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getDao(): AnimeDao

    companion object{
        @Volatile
        private var INSTANCE: com.example.animelist.db.Database? = null

        fun getDatabase(context: Context): com.example.animelist.db.Database{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, com.example.animelist.db.Database::class.java, "database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}