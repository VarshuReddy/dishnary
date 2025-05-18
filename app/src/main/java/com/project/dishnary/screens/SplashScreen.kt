package com.project.dishnary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.dishnary.R
import com.project.dishnary.ui.theme.DishnaryLogo
import com.project.dishnary.ui.theme.DishnaryTheme

@Composable
fun SplashScreen() {

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
        SplashScreen()
    }
}