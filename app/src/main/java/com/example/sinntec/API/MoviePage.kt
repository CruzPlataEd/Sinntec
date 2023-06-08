package com.example.sinntec.API

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviePage(
    @Json(name="page")
    val page: Int,
    @Json(name = "results")
    val results: ArrayList<MovieItem>
)
