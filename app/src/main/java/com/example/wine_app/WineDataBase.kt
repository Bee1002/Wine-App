package com.example.wine_app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wine::class], version = 1)
@TypeConverters(WineConverters::class)
abstract class WineDataBase : RoomDatabase(){
    abstract fun wineDao(): WineDAO
}