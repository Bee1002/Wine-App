package com.example.wine_app

import android.app.Application
import androidx.room.Room

class WineApplication : Application() {
    companion object {
        lateinit var database: WineDataBase

    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
        WineDataBase::class.java,
            "WineDataBase")
            .build()
    }
}