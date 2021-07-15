package com.suryasa.made.maps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.suryasa.made.core.data.Resource
import com.suryasa.made.maps.databinding.ActivityMapsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {
    private var _binding: ActivityMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MapsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(mapsModule)

        supportActionBar?.title = "POPular moVIES"

        getPopMovies()
    }

    private fun getPopMovies() {
        viewModel.movie.observe(this, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        binding.tvMaps.text = "This is map of ${movies.data?.get(9)?.title}"
                    }
                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvMaps.text = movies.message
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}