package com.project.dishnary.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.dishnary.ui.theme.Blue
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.Tomato
import com.project.dishnary.ui.theme.White
import com.project.dishnary.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val scrollBehaviorTop = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(
                topBar = {
                        TopAppBar(
                            title = {
                                Column {
                                    Text("Find Recipes ",
                                        style = MaterialTheme.typography.titleLarge.copy(),
                                        color = Tomato)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Select ingredients to search for recipes",
                                        style = MaterialTheme.typography.labelSmall)
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = White
                            ),
                        )
                }
    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun preview(){
    DishnaryTheme {
        SearchScreen()
    }
}

