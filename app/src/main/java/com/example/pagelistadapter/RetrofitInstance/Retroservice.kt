package com.example.pagelistadapter.RetrofitInstance

import RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query

interface Retroservice {
    @GET("character")
    suspend fun getDataFromAPI(@Query("page")query:Int):RickAndMortyList
}