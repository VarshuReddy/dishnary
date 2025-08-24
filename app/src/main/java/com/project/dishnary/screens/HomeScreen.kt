package com.project.dishnary.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.dishnary.screens.searchScreens.SearchScreen
import com.project.dishnary.sealedClasses.BottomNav
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.Orange
import com.project.dishnary.ui.theme.OrangeRed
import com.project.dishnary.ui.theme.White
import kotlinx.coroutines.launch


@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val items = listOf(BottomNav.Search, BottomNav.Recipes , BottomNav.Profile)
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { items.size }
    )

    Scaffold (
        bottomBar = {
            NavigationBar(
                containerColor = White,
                tonalElevation = 3.dp,
            ) {
                items.forEachIndexed { index,item ->
                    val selected = pagerState.currentPage == index
                    NavigationBarItem(
                        selected = selected,

                        onClick = {
                            scope.launch{ pagerState.animateScrollToPage(index)}
                        },
                        modifier =  if (selected) {
                            Modifier
                                .padding(10.dp,1.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    OrangeRed.copy(alpha = 0.3f)
                                )
                        } else Modifier,
                        icon  = {
                            if(selected){
                                Icon(
                                    painterResource(item.iconSelected),
                                    contentDescription = item.title,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(30.dp)
                                )
                            }else{
                                Icon(
                                    painterResource(item.iconUnselected),
                                    contentDescription = item.title,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        } ,
                        label = {Text(item.title,
                            fontSize = 12.sp
                            )
                           },

                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Orange,
                            selectedTextColor = OrangeRed,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }

        },
        topBar = {}
    ){padding ->
        HorizontalPager(state = pagerState ,
            modifier = Modifier.fillMaxSize().padding(padding)) {
            when(it){
                0 -> SearchScreen()
                1 -> RecipesScreen()
                2 -> ProfileScreen()
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    DishnaryTheme {
        HomeScreen()
    }
}