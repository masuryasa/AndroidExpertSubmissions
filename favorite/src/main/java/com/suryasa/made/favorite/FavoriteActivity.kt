package com.suryasa.made.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.suryasa.made.R
import com.suryasa.made.core.ui.HomeAdapter
import com.suryasa.made.detail.DetailActivity
import com.suryasa.made.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorite_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = HomeAdapter()
        adapter.onItemClick = { selectedMovie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
            startActivity(intent)
        }

        viewModel.favoriteMovies.observe(this, { movies ->
            val emptyView: View = binding.dataEmpty as View

            if (movies != null) {
                adapter.setMovies(movies)
            }
            emptyView.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvFavorite) {
            this.layoutManager = LinearLayoutManager(this.context)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}