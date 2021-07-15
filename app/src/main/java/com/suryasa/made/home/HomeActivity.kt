package com.suryasa.made.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.suryasa.made.R
import com.suryasa.made.core.data.Resource
import com.suryasa.made.core.ui.HomeAdapter
import com.suryasa.made.databinding.ActivityMainBinding
import com.suryasa.made.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.main_title)

        val adapter = HomeAdapter()
        adapter.onItemClick = { selectedMovie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
            startActivity(intent)
        }

        viewModel.movies.observe(this, { movies ->
            Log.d("TAG", "dataaa: $movies")
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        adapter.setMovies(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvMovie) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        try {
            when (item.itemId) {
                R.id.action_bookmark -> {
                    moveToFavoriteActivity()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveToFavoriteActivity() {
        startActivity(Intent(this, Class.forName("com.suryasa.made.favorite.FavoriteActivity")))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}