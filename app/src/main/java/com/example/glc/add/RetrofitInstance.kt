package com.example.glc.add

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Nie będzie potrzebny, gdy pojawi się Dagger, Instance będzie brana z Module
object RetrofitInstance {

    val api: TwichApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.igdb.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwichApi::class.java)
    }
}