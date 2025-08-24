package com.project.dishnary.utils

import com.project.dishnary.R

object CategoryIcons {
    private val map = mapOf(
        "veggies" to R.drawable.veggies_ic,
        "fruits" to R.drawable.fruits_ic,
        "pantry" to R.drawable.baking_ic,
        "dairy" to R.drawable.diary_ic,
        "proteins" to R.drawable.proteins_ic,
        "grains" to R.drawable.grains_ic,
        "herbs" to R.drawable.herbs_ic
    )

    fun getIconFor(category: String): Int {
        return map.entries.firstOrNull { category.contains(it.key, ignoreCase = true) }?.value
            ?: R.drawable.groceries
    }
}