package com.example.majorbookservice.Data.DTO

data class BookDto(
    val author: String,
    val id: Int,
    val isSaved: Boolean,
    val publisher: String,
    val title: String,
    val type: String,
    val year: Int
)