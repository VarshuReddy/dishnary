package com.project.dishnary.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.dishnary.ui.theme.Black
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.OrangeRed
import com.project.dishnary.ui.theme.PurpleGrey40
import com.project.dishnary.ui.theme.Red
import com.project.dishnary.ui.theme.SlateGray
import com.project.dishnary.ui.theme.Tomato
import com.project.dishnary.viewmodel.AuthState
import com.project.dishnary.viewmodel.AuthenticationVM

@Composable
fun LoginScreen(navControl: NavHostController, authVm: AuthenticationVM) {

    val authState by authVm.authstate.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        if(authState is AuthState.Authenticated){
            navControl.navigate("home"){
                popUpTo("login"){inclusive=true}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize().offset(y = -50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Log in",
            modifier = Modifier.offset(y = -50.dp),
            textAlign = TextAlign.Center,
            color = PurpleGrey40,
            style= MaterialTheme.typography.titleLarge.copy(fontSize = 70.sp),
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp, 50.dp, 0.dp),
            text = "Email",
            textAlign = TextAlign.Left,
            color = PurpleGrey40,
            style= MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("") },
            modifier = Modifier.fillMaxWidth().
            padding(50.dp, 0.dp, 50.dp, 0.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = SlateGray,
                focusedBorderColor = Tomato,
                focusedLabelColor = Black,
                cursorColor = Black,
                focusedTextColor = Black,
                errorBorderColor = OrangeRed
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            maxLines = 1,

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp, 50.dp, 0.dp),
            text = "Password",
            textAlign = TextAlign.Left,
            color = PurpleGrey40,
            style= MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().
            padding(50.dp, 0.dp, 50.dp, 0.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = SlateGray,
                focusedBorderColor = Tomato,
                focusedLabelColor = Black,
                cursorColor = Black,
                focusedTextColor = Black,
                errorBorderColor = OrangeRed
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            maxLines = 1,

            )
        Spacer(modifier = Modifier.height(20.dp))

        Button(shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(16.dp),
            onClick = {
                    authVm.login(email,password)
            }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp, 50.dp, 0.dp)
                .clickable {navControl.navigate("signup") },
            text = "New Here? Sign Up!",
            textAlign = TextAlign.Center,
            color = PurpleGrey40,
            style= MaterialTheme.typography.labelSmall,

        )
    }

    when (authState) {
        is AuthState.Error -> Text((authState as AuthState.Error).message, color = Red)
        is AuthState.Loading -> CircularProgressIndicator()
        else -> {}
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    DishnaryTheme {
        LoginScreen(
            rememberNavController(),
            hiltViewModel()
        )
    }
}