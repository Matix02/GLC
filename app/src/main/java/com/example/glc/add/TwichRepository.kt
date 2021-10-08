package com.example.glc.add

import com.example.glc.add.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TwichRepository @Inject constructor(private val twichApi: TwichApi) {

    //OnlyBody how about Errors?
    //https://developer.android.com/kotlin/coroutines
    suspend fun searchGames(title: String): Response<Game> {
        return withContext(Dispatchers.IO) { twichApi.getGamesBySearch(title) }
    }
}