package com.example.pagelistadapter.RetrofitInstance

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val baseUrl="https://rickandmortyapi.com/api/"

        fun getRetrofitInstance():Retrofit{
            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}