package com.example.glc

//test constructor class
data class Game(val title: String)


fun getSampleGames(): List<Game> {
    return listOf(
        Game("Doom Eternal"),

        Game("Dark Souls III")
    )
}
