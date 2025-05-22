package com.example.wine_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wine_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WineListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()

        }

    private fun setupAdapter() {
        adapter = WineListAdapter()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = this@MainActivity.adapter
        }
    }

    private fun getWines() {
        val wines = getLocalWines()
        adapter.submitList(wines)
    }

    private fun getLocalWines() = listOf(Wine("Toro", "Rosa",
        Rating("5.5", "236 ratings"), "Ecuador",
        "https://images.vivino.com/thumbs/l_eXmV1KTdGzz3ky_Qey3A_pb_x300.png", 1))


    override fun onResume() {
        super.onResume()
        getWines()
    }
}