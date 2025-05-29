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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host, homeFragment)
            .commit()
        }

    /*private fun getLocalWines() = listOf(Wine("Maselva", "Emporda 2012",
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

*/



}