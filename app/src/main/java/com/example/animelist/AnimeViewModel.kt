package com.example.animelist

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.backend.Anime
import com.example.animelist.backend.Server
import com.example.animelist.db.AnimeDao
import com.example.animelist.db.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AnimeViewModel: ViewModel() {
    lateinit var db: Database
    lateinit var dao: AnimeDao
    lateinit var state: StateFlow<List<Anime>>

    fun initializeDb(context: Context){
        db = Database.getDatabase(context)
        dao = db.getDao()
        state = dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    fun insertAnimeIntoDb(context: Context){
        viewModelScope.launch {
            val db = Database.getDatabase(context)
            val dao = db.getDao()
            if (state.value.isEmpty()){
                Server.animeList().forEach{ anime ->
                    dao.insert(anime)
                }
            }

//            val animeList: ArrayList<Anime> = ArrayList()
//            val job = CoroutineScope(Dispatchers.IO).launch {
//                animeList.addAll(Server.animeList())
//            }
//            job.join()
//            return animeList
        }
    }

    fun isWatching(animeId: Int){
        viewModelScope.launch {
            val updatedAnime = state.value.find{it.id == animeId}
            updatedAnime!!.isWatching = !updatedAnime.isWatching
            dao.update(updatedAnime)
        }
    }

    fun isWantToWatch(animeId: Int){
        viewModelScope.launch {
            val updatedAnime = state.value.find{it.id == animeId}
            updatedAnime!!.isWantToWatch = !updatedAnime.isWantToWatch
            dao.update(updatedAnime)
        }
    }

    fun isWatched(animeId: Int){
        viewModelScope.launch {
            val updatedAnime = state.value.find{it.id == animeId}
            updatedAnime!!.isWatched = !updatedAnime.isWatching
            dao.update(updatedAnime)
        }
    }
}