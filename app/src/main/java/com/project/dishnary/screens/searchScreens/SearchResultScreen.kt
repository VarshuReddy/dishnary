package com.project.dishnary.screens.searchScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.dishnary.model.Recipe
import com.project.dishnary.sealedClasses.UiState
import com.project.dishnary.viewmodel.SearchVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    navController: NavController,
    viewModel: SearchVm = hiltViewModel()
) {
    val recipeState by viewModel.recipeState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Recipes")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        when (val state = recipeState) {

            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(padding)
                )
            }

            is UiState.Error -> {
                Text(
                    text = state.message,
                    modifier = Modifier.padding(padding)
                )
            }

            is UiState.Success -> {
                val recipes = state.data
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                   items(items = recipes){
                       RecipeCard(it)
                   }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "${recipe.ingredients.size} ingredients",
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = "${recipe.time} min",
            style = MaterialTheme.typography.bodySmall
        )
    }
}