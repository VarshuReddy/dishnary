package com.project.dishnary.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.dishnary.R
import com.project.dishnary.ui.theme.Black
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.OrangeRed
import com.project.dishnary.ui.theme.PurpleGrey40
import com.project.dishnary.ui.theme.SlateGray
import com.project.dishnary.ui.theme.Tomato

@Composable
fun SignUpScreen(navControl: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize().offset(y = -50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign up",
            modifier = Modifier.offset(y = -50.dp),
            textAlign = TextAlign.Center,
            fontSize = 70.sp,
            fontStyle = FontStyle.Normal,
            color = PurpleGrey40,
            fontFamily = FontFamily(Font(R.font.delius_swashcaps_regular))
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp, 50.dp, 0.dp),
            text = "Email",
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            color = PurpleGrey40,

            fontFamily = FontFamily(Font(R.font.delius_swashcaps_regular))
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
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            color = PurpleGrey40,

            fontFamily = FontFamily(Font(R.font.delius_swashcaps_regular))
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
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    DishnaryTheme {
        SignUpScreen(rememberNavController())
    }
}