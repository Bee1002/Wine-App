package com.example.wine_app

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wine_app.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WineListAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: WineService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupRetrofit()
        setupSwipeRefresh()

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

    private fun setupSwipeRefresh() {
        binding.srlWines.setOnRefreshListener {
            adapter.submitList(listOf())
            getWines()
        }
    }

    private  fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(WineService::class.java)

    }

    private fun getWines() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {//val wines = getLocalWines()
                val serverOK = Random.nextBoolean()
                val wines = if (serverOK) service.getRedWines() else listOf()
                withContext(Dispatchers.Main) {
                    if (wines.isNotEmpty()) {
                        showRecyclerView(true)
                        showNoDataView(false)
                        adapter.submitList(wines)
                    } else {
                        showRecyclerView(false)
                        showNoDataView(true)
                    }
                }
            }  catch (e: Exception) {
                showMsg((R.string.common_request_fail))
                } finally {
                    showProgress(false)
                }

        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()}

    private  fun showRecyclerView(isVisible: Boolean) {
        binding.recyclerView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

    private  fun showNoDataView(isVisible: Boolean) {
        binding.tvNoData.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

    private  fun showProgress(isVisible: Boolean) {
        binding.srlWines.isRefreshing = isVisible
    }

    private fun getLocalWines() = listOf(Wine("Maselva", "Emporda 2012",
        Rating("4.9", "88 ratings"), "Spain\\n·\\nEmpordà",
        "https://images.vivino.com/thumbs/ApnIiXjcT5Kc33OHgNb9dA_375x500.jpg", 1),
    Wine("Perro", "Rojo",
    Rating("5.6", "237 ratings"), "Mexico",
    "https://images.vivino.com/thumbs/nC9V6L2mQQSq0s-wZLcaxw_pb_x300.png", 2),
    Wine("Gato", "Gris",
    Rating("5.5", "238 ratings"), "Colombia",
    "https://images.vivino.com/thumbs/L33jsYUuTMWTMy3KoqQyXg_pb_x300.png", 3),
    Wine("Gallina", "Blanco",
    Rating("5.5", "239 ratings"), "Peru",
    "https://images.vivino.com/thumbs/GpcSXs2ERS6niDxoAsvESA_pb_x300.png", 4))


    override fun onResume() {
        super.onResume()
        showProgress(true)
        getWines()
    }
}