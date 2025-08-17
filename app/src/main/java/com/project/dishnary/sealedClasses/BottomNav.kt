package com.project.dishnary.sealedClasses

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.project.dishnary.R

sealed class BottomNav (val title: String, val iconSelected: Int , val iconUnselected:Int){
    object Search: BottomNav("Search", R.drawable.groceries, R.drawable.groceries__unselected)
    object Recipes: BottomNav("Recipes", R.drawable.recipe,R.drawable.recipe__unselected)
    object Profile: BottomNav("Profile", R.drawable.chef,R.drawable.chef__unselected)

}