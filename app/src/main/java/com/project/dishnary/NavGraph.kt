package com.project.dishnary

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.dishnary.screens.LoginScreen
import com.project.dishnary.screens.SignUpScreen
import com.project.dishnary.screens.SplashScreen

@Composable
fun Navigate(){
    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = "splash"){
        composable(route = "splash"){
            SplashScreen(navControl)
        }
        composable(route = "login"){
            LoginScreen(navControl)
        }
        composable("signup"){
            SignUpScreen(navControl)
        }
    }
}