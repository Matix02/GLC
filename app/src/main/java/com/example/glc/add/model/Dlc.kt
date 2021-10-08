package com.example.glc.add.model

data class Dlc(
    val category: Int,
    val checksum: String,
    val created_at: Int,
    val external_games: List<Int>,
    val id: Int,
    val name: String,
    val parent_game: Int,
    val screenshots: List<Int>,
    val slug: String,
    val summary: String,
    val updated_at: Int,
    val url: String,
    val websites: List<Int>
)