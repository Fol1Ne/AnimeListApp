package com.example.animelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animelist.backend.Anime
import com.example.animelist.databinding.FragmentMainMenuBinding
import com.example.animelist.db.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuFragment : Fragment() {
    lateinit var title: TextView
    lateinit var type: TextView
    lateinit var averageRating: TextView
    lateinit var animeViewModel: AnimeViewModel
    lateinit var recViewAdapter: RecViewAdapter
    lateinit var animeList: List<Anime>

    private lateinit var _binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animeViewModel = AnimeViewModel()

        animeViewModel.initializeDb(requireContext())

        recViewAdapter = RecViewAdapter{
            view.findNavController().navigate(R.id.animePageFragment, bundleOf("id" to it))
        }

        _binding.recView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recViewAdapter
        }
    }
}