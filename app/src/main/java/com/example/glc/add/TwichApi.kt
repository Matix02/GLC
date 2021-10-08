package com.example.glc.add

import com.example.glc.add.model.Game
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TwichApi {

    //Two FIELDS
    @Headers(
        "Client-ID: i3nzc6w3n0pod39zgsq8h445o2yp6p",
        "Authorization: Bearer 4dyefeh651k507cwwbovm20lp9jchb",
        "Accept: application/json"
    )
    @POST("games/")
    suspend fun getGamesWithParameters(
        @Query("fields") fields: String
    ): Response<Game>

    //WHERE Test
    @Headers(
        "Client-ID: i3nzc6w3n0pod39zgsq8h445o2yp6p",
        "Authorization: Bearer 4dyefeh651k507cwwbovm20lp9jchb",
        "Accept: application/json"
    )
    @POST("games/")
    suspend fun getGamesWithWhere(
        @Query("fields") fields: String
    ): Response<Game>

    //SORT Test
    @Headers(
        "Client-ID: i3nzc6w3n0pod39zgsq8h445o2yp6p",
        "Authorization: Bearer 4dyefeh651k507cwwbovm20lp9jchb",
        "Accept: application/json"
    )
    @POST("games/")
    suspend fun getGamesWithSort(
        @Query("fields") fields: String
    ): Response<Game>

    //SORT Test
    @Headers(
        "Client-ID: i3nzc6w3n0pod39zgsq8h445o2yp6p",
        "Authorization: Bearer 4dyefeh651k507cwwbovm20lp9jchb",
        "Accept: application/json"
    )
    @POST("games/")
    suspend fun getGamesBySearch(
        @Query("fields") fields: String
    ): Response<Game>

    /*
    1. !!WHERE
    2. !!SORT
    3. SEARCH
    4. Return with Array
    4.
     */


}