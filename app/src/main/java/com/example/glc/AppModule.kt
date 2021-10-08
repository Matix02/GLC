package com.example.glc

import com.example.glc.add.TwichApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): TwichApi {
        return Retrofit.Builder()
            .baseUrl("https://api.igdb.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwichApi::class.java)
    }
}