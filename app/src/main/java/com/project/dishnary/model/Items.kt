package com.project.dishnary.model

data class Items(
    val name:String,
    val items: List<String>
)

data class Recipe(
    val id: String = "",
    val title: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val preparationTime: Int = 0,
    val imageUrl: String = "",
    val tags: List<String> = emptyList(),
    val authorName: String = "",
    val authorId: String = "",
    val videoUrl: String = ""
)