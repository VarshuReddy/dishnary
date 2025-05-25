package com.project.dishnary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.dishnary.R
import com.project.dishnary.ui.theme.DishnaryLogo
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.viewmodel.AuthState
import com.project.dishnary.viewmodel.AuthenticationVM
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navControl: NavHostController, authVm: AuthenticationVM) {

    val authState = authVm.authstate.collectAsState()
    LaunchedEffect(Unit) {
        delay(3000)
        when(authState){
            AuthState.Authenticated -> {
                navControl.navigate("home"){
                    popUpTo("splash"){ inclusive = true}
                }
            }
            AuthState.UnAuthenticated -> {
                navControl.navigate("login"){
                    popUpTo("splash"){ inclusive = true}
                }
            }
            AuthState.Loading -> {}
            else -> Unit
        }
        navControl.navigate("login"){
            popUpTo("splash"){ inclusive = true}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.splash_bg),
                contentScale = ContentScale.FillBounds
            ), contentAlignment = Alignment.Center
    ) {

        Text(
            text = "dishnary",
            modifier = Modifier.fillMaxWidth().offset(y = -10.dp),
            style = DishnaryLogo,
            textAlign = TextAlign.Center,

        )

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DishnaryTheme {
        SplashScreen(rememberNavController(), hiltViewModel())
    }
}