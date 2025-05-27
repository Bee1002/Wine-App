package com.example.wine_app

import androidx.room.TypeConverter
import com.google.gson.Gson

class WineConverters {
    @TypeConverter
    fun fromJsonStr(value: String?): Rating? {
        return value?.let { Gson().fromJson(it, Rating::class.java)}
    }

    @TypeConverter
    fun fromRating(values: Rating?): String? {
        return values?.let { Gson().toJson(it)}
    }
}