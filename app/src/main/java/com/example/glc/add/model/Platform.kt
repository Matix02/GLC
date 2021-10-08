package com.example.glc.add.model

data class Platform(
    val abbreviation: String,
    val alternative_name: String,
    val category: Int,
    val checksum: String,
    val created_at: Int,
    val generation: Int,
    val id: Int,
    val name: String,
    val platform_family: Int,
    val platform_logo: Int,
    val slug: String,
    val summary: String,
    val updated_at: Int,
    val url: String,
    val versions: List<Int>,
    val websites: List<Int>
)