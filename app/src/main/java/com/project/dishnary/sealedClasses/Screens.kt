package com.project.dishnary.sealedClasses

enum class Screen {
    SPLASH, LOGIN, SIGNUP, HOME
}

sealed class Screens(val route: String) {
    object Login : Screens(Screen.LOGIN.name)
    object Signup : Screens(Screen.SIGNUP.name)
    object Home : Screens(Screen.HOME.name)
    object Splash : Screens(Screen.SPLASH.name)
}