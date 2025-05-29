package com.example.wine_app

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class FavouriteFragment : BaseFragment(), OnClickListener {

    private lateinit var adapter: WineListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setupAdapter()
        setupRecyclerView()
        setupSwipeRefresh()

    }

    private fun setupAdapter() {
        adapter = WineListAdapter()
        adapter.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavouriteFragment.adapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlWines.setOnRefreshListener {
            adapter.submitList(listOf())
            getWines()
        }
    }

    private fun getWines() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val wines = WineApplication.database.wineDao().getAllWines()
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
    /*
    * OnClickListener
    * */
    override fun onLongClick(wine: Wine) {

    }

    override fun onFavourite(wine: Wine) {

    }
}