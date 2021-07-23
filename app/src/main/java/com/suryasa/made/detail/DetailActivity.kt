package com.suryasa.made.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suryasa.made.R
import com.suryasa.made.core.domain.model.Movie
import com.suryasa.made.databinding.ActivityDetailBinding
import com.suryasa.made.databinding.ContentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var contentBinding: ContentBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = getString(R.string.detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (extras != null) {
            val id = extras.movieId
            viewModel.setSelectedMovie(id)
            viewModel.getMovie().observe(this, { movie ->
                activityDetailBinding.progressbar.visibility = View.GONE
                activityDetailBinding.content.visibility = View.VISIBLE
                movie.data?.let { populateMovie(it) }
            })
        }
    }

    private fun populateMovie(movie: Movie) {
        contentBinding.textTitle.text = movie.title
        val rate = movie.rating.toFloat()/2.0
        val dec =  DecimalFormat("#,##")
        contentBinding.ratingNum.text = movie.rating
        contentBinding.ratingBar.rating = dec.format(rate).toFloat()
        contentBinding.release.text = movie.releaseDate
        contentBinding.overview.text = movie.overview

        Glide.with(this)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + movie.posterUrl)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(contentBinding.imgPoster)

        viewModel.checkMovieFavorite(movie.movieId).observe(this) {
            contentBinding.favorite.setOnClickListener{
                val movieStatus = !movie.favorite
                viewModel.setFavoriteMovie(movie, movieStatus)
            }
            setFavoriteIcon(it)
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        with(contentBinding) {
            if (state) favorite.setImageResource(R.drawable.ic_bookmarked)
            else favorite.setImageResource(R.drawable.ic_bookmark)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}