package com.project.dishnary

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.dishnary.screens.HomeScreen
import com.project.dishnary.screens.LoginScreen
import com.project.dishnary.screens.SignUpScreen
import com.project.dishnary.screens.SplashScreen
import com.project.dishnary.sealedClasses.Screens
import com.project.dishnary.viewmodel.AuthenticationVM

@Composable
fun Navigate(authVm: AuthenticationVM) {


    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = "splash"){
        composable(Screens.Splash.route){
            SplashScreen(navControl,authVm)
        }
        composable(Screens.Login.route){
            LoginScreen(navControl,authVm)
        }
        composable(Screens.Signup.route){
            SignUpScreen(navControl,authVm)
        }
        composable(Screens.Home.route) {
            HomeScreen()
        }
    }
}

