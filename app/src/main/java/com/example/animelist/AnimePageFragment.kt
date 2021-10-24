package com.example.animelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.animelist.backend.Anime
import com.example.animelist.databinding.FragmentAnimePageBinding
import com.example.animelist.db.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimePageFragment : Fragment(), View.OnClickListener {

    lateinit var currentAnime: Anime
    lateinit var animeViewModel: AnimeViewModel

    private lateinit var _binding: FragmentAnimePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnimePageBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animeViewModel = AnimeViewModel()
        _binding.title.text = ("Title: " + currentAnime.attributes.canonicalTitle)
        _binding.type.text = ("Type: " + currentAnime.type)
        _binding.averageRating.text = ("Average Rating: " + currentAnime.attributes.averageRating.toString())
        _binding.description.text = ("Description: " + currentAnime.attributes.description)
        Glide
            .with(view)
            .load(currentAnime.attributes.posterImage.originalImage)
            .centerCrop()
            .into(_binding.posterImage)
        Glide
            .with(view)
            .load(currentAnime.attributes.coverImage.originalCoverImage)
            .centerCrop()
            .into(_binding.coverImage)

        if (currentAnime.isWatching){
            _binding.viewCategory.text = ("View Category: Watching")
        }
        else if (currentAnime.isWantToWatch){
            _binding.viewCategory.text = ("View Category: Want To Watch")
        }
        else if (currentAnime.isWatched){
            _binding.viewCategory.text = ("View Category: Watched")
        }

        initState()
    }

    override fun onClick(v: View?) {
        when(v!!.getId()){
            R.id.watchingBtn -> {
                animeViewModel.isWatching(currentAnime.id)
            }
            R.id.wantToWatchBtn -> {
                animeViewModel.isWantToWatch(currentAnime.id)
            }
            R.id.watchedBtn -> {
                animeViewModel.isWatched(currentAnime.id)
            }
        }
    }

    fun initState(){
        val recViewAdapter = RecViewAdapter{
            view?.findNavController()?.navigate(R.id.animePageFragment, bundleOf("id" to it))
        }

        lifecycleScope.launch {
            animeViewModel.state.collect {
                recViewAdapter.submitList(it)
            }
        }
    }
}