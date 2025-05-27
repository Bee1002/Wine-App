package com.example.wine_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WineDAO {
    @Query("SELECT * FROM WineEntity")
    fun getAllWines(): MutableList<Wine>

    @Insert
    fun addWine(wine: Wine): Long
}