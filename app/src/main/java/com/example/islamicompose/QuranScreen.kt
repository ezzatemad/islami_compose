package com.example.islamicompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.Suraslist


@Composable
fun QuranScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.iv_quran_header),
                contentDescription = null,
                Modifier
                    .fillMaxHeight(.3f)
                    .fillMaxWidth(.6f),
            )
            HorizontalLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)
            )
            Text(
                "اسم السورة",
                fontSize = 32.sp,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            HorizontalLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )


            // Use LazyColumn to display the sura names
            LazyColumn {
                items(Suraslist) { suraName ->
                    SuraNames(suraName)
                }
            }
        }
    }
}

@Composable
fun SuraNames(suraName: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            suraName,
            color = Color.Black,
            fontSize = 27.sp,
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )

        HorizontalLine(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(top = 10.dp, bottom = 10.dp)
        )
    }
}

@Composable
fun HorizontalLine(modifier: Modifier) {
    Divider(
        color = colorResource(R.color.gold),
        thickness = 1.dp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun QuranPreview() {
    MainScreen()
}