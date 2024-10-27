package com.example.islamicompose.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.islamicompose.mainactivity.MainActivity
import com.example.islamicompose.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()

            LaunchedEffect(Unit) {
                delay(2000)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}


@Composable
fun SplashScreen() {
    Image(
        painter = painterResource(R.drawable.splash_light),
        contentDescription = "splash screen",
        modifier = Modifier.fillMaxSize(1f),
        contentScale = ContentScale.FillBounds
    )
}


@Preview(showBackground = true)
@Composable
fun SplashPreview1() {
    SplashScreen()
}