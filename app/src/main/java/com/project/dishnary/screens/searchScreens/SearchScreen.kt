package com.project.dishnary.screens.searchScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.dishnary.model.Items
import com.project.dishnary.sealedClasses.UiState
import com.project.dishnary.ui.theme.Coral
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.Red
import com.project.dishnary.ui.theme.Snow
import com.project.dishnary.ui.theme.Tomato
import com.project.dishnary.ui.theme.White
import com.project.dishnary.viewmodel.SearchVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewmodel : SearchVm = hiltViewModel()
) {
    val scrollBehaviorTop = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    val uiState  by viewmodel.uiState.collectAsState()


    Scaffold(
                containerColor = Coral.copy(alpha = 0.9f),
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
    ){ p ->
        Surface(
            modifier = Modifier
                .padding(p).padding(12.dp,20.dp)
                .fillMaxSize(),
            color = Snow,
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 10.dp
        ) {
            when(uiState){
               is UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                   CircularProgressIndicator()
               }
                is UiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error loading ingredients 😢", color = Red)
                }
                is UiState.Success ->{
                    val itemsL = (uiState as UiState.Success<List<Items>>).data
                    LazyColumn {
                        items(itemsL){itemsList->
                            ListItemExpandable(itemsList,viewmodel)
                        }
                    }
                }

               }

        }
    }
}

@Preview
@Composable
fun preview(){
    DishnaryTheme {
        SearchScreen()
    }
}

