package com.project.dishnary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.viewmodel.AuthenticationVM
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DishnaryTheme() {
                val authVm : AuthenticationVM  = hiltViewModel()
                Navigate(authVm)
            }
        }
    }
}


