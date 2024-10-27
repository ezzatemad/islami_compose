package com.example.islamicompose.radio

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetRadioUseCase
import com.example.islamicompose.R


@Composable
fun RadioScreen(
    viewModel: RadioViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.processIntent(RadioIntent.FetchRadios)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.rad),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.5f)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items(state.radios) { radio ->
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = radio.name,
                            fontSize = 25.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.islami_word_Arabic)
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.stop),
                                contentDescription = "stop",
                                modifier = Modifier.clickable {
                                    viewModel.processIntent(RadioIntent.StopRadio)

                                    val stopIntent =
                                        Intent(context, RadioNotification::class.java).apply {
                                            action = "STOP"
                                        }
                                    context.startService(stopIntent)
                                }
                            )

                            Image(
                                painter = painterResource(R.drawable.iv_radio_play),
                                contentDescription = "play",
                                modifier = Modifier.clickable {
                                    viewModel.processIntent(RadioIntent.PlayRadio(radio.url))
                                    val playIntent =
                                        Intent(context, RadioNotification::class.java).apply {
                                            action = "PLAY"
                                            putExtra("RADIO_URL", radio.url)
                                            putExtra("RADIO_TITLE", radio.name)
                                        }
                                    context.startService(playIntent)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContentttPreview() {
    RadioScreen()
}