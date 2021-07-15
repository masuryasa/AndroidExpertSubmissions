package com.suryasa.made.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suryasa.made.core.R
import com.suryasa.made.core.databinding.ItemsBinding
import com.suryasa.made.core.domain.model.Movie

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    private var listMovies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
//    private var onItemClickCallback: OnItemClickCallback? = null

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) =
            ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.items, viewGroup, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount() = listMovies.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemOverview.text = movie.overview
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + movie.posterUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovies[adapterPosition])
            }
        }
    }

//    interface OnItemClickCallback {
//        fun onItemClicked(data: MovieEntity)
//    }

}