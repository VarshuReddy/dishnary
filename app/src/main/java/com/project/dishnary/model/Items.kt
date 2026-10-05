package com.project.dishnary.model

data class Items(
    val name:String,
    val items: List<String>
)

data class Recipe(
    val id: String = "",
    val name: String = "",
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val time: Int = 0,
    val image_url: String = "",
    val description:String =""
)