package com.example.glc.add.model

data class Cover(
    val alpha_channel: Boolean,
    val animated: Boolean,
    val checksum: String,
    val game: Int,
    val height: Int,
    val id: Int,
    val image_id: String,
    val url: String,
    val width: Int
)