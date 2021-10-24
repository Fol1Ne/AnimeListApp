package com.example.animelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animelist.backend.Anime
import com.example.animelist.backend.PosterImage
import com.example.animelist.databinding.FragmentMainMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecViewAdapter(val callback: (id: Int) -> Unit): ListAdapter<Anime, RecViewAdapter.AnimeViewHolder>(AnimeFeedDiffCallback()) {
    class AnimeViewHolder(var view: View): RecyclerView.ViewHolder(view){

        lateinit var title: TextView
        lateinit var type: TextView
        lateinit var averageRating: TextView
        lateinit var posterImage: ImageView

        fun onBind(item: Anime, callback: (id: Int) -> Unit){
            title = view.findViewById(R.id.title)
            type = view.findViewById(R.id.type)
            averageRating = view.findViewById(R.id.averageRating)
            posterImage = view.findViewById(R.id.posterImage)

            title.text = ("Title: " + item.attributes.canonicalTitle)
            type.text = ("Type: " + item.type)
            averageRating.text = ("Average Rating: " + item.attributes.averageRating.toString())
            Glide
                .with(view)
                .load(item.attributes.posterImage.originalImage)
                .centerCrop()
                .into(posterImage)

            itemView.setOnClickListener{
                callback.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.anime_item, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.onBind(getItem(position), callback)
    }
}

class AnimeFeedDiffCallback: DiffUtil.ItemCallback<Anime>() {
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.attributes.canonicalTitle == newItem.attributes.canonicalTitle
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}