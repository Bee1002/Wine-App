package com.example.wine_app

import retrofit2.http.GET

interface WineService {
    @GET(Constants.PARH_WINES)
    suspend fun getRedWines() : List<Wine>
}