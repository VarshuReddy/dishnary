package com.project.dishnary.screens.searchScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.dishnary.model.Items
import com.project.dishnary.sealedClasses.UiState
import com.project.dishnary.ui.theme.Coral
import com.project.dishnary.ui.theme.Red
import com.project.dishnary.ui.theme.Snow
import com.project.dishnary.ui.theme.Tomato
import com.project.dishnary.ui.theme.White
import com.project.dishnary.viewmodel.SearchVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewmodel : SearchVm = hiltViewModel()
) {
    val scrollBehaviorTop = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    val uiState  by viewmodel.uiState.collectAsState()

    val selected by viewmodel.selectedItems.collectAsState()
    var showSheet by remember {
        mutableStateOf(false)
    }

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
    ) { p ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(p)
        ) {

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 5.dp
                    ),
                color = Snow,
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 10.dp
            ) {

                when (uiState) {

                    is UiState.Loading -> {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UiState.Error -> {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Error loading ingredients 😢",
                                color = Red
                            )
                        }
                    }

                    is UiState.Success -> {

                        val itemsL =
                            (uiState as UiState.Success<List<Items>>).data

                        LazyColumn {

                            items(
                                items = itemsL,
                                key = { it.name }
                            ) { itemsList ->

                                ListItemExpandable(
                                    itemsList,
                                    selected,
                                    onToggle = {
                                        viewmodel.toggleSelection(it)
                                    },
                                    expanded =
                                        viewmodel.expanded[itemsList.name]
                                            ?: true,
                                    onExpandToggle = {
                                        viewmodel.toggleExpanded(
                                            itemsList.name
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // 👇 This is the ONLY selected ingredients bar
            if (selected.isNotEmpty()) {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 8.dp
                        )
                        .clickable {
                            showSheet = true
                        },
                    shape = RoundedCornerShape(15.dp),
                    color = Tomato,
                    shadowElevation = 6.dp
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "${selected.size} ingredients selected",
                            color = White
                        )

                        Text(
                            text = "View →",
                            color = White
                        )
                    }
                }
            }
        }
    }
    if (showSheet) {

        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
            }
        ) {

            SelectedIngredientsSheet(
                selectedItems = selected,
                onRemove = {
                    viewmodel.toggleSelection(it)
                },
                onSearch = {
                    viewmodel.searchRecipes()
                    navController.navigate("search_results")
                    showSheet = false
                }
            )
        }
    }
    if (selected.isNotEmpty()) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .clickable {
                    showSheet = true
                },
            shape = RoundedCornerShape(15.dp),
            color = Tomato,
            shadowElevation = 6.dp
        ) {

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    "${selected.size} ingredients selected",
                    color = White
                )

                Text(
                    "View →",
                    color = White
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedIngredientsSheet(
    selectedItems: Set<String>,
    onRemove: (String) -> Unit,
    onSearch: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text(
            text = "Your Ingredients",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${selectedItems.size} ingredients selected",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            selectedItems.forEach { ingredient ->

                AssistChip(
                    onClick = {
                        onRemove(ingredient)
                    },
                    label = {
                        Text(ingredient)
                    },
                    trailingIcon = {
                        Text("×")
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Tomato.copy(alpha = 0.15f),
                        labelColor = Tomato
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSearch,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Find Recipes")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}


