package com.example.wine_app

data class Wine(
    val winery: String,
    val wine: String,
    val rating: Rating,
    val location: String,
    val image: String,
    val id: Int)