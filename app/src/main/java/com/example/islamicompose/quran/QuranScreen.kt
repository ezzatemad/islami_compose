package com.example.islamicompose.quran

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Suraslist
import com.example.islamicompose.R
import com.example.islamicompose.mainactivity.MainScreen
import com.example.islamicompose.qurencontent.QuranContentActivity


@Composable
fun QuranScreen() {

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.iv_quran_header),
                contentDescription = stringResource(R.string.quran_image_header),
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
                stringResource(R.string.sura_name_header),
                fontSize = 32.sp,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            HorizontalLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )


            LazyColumn {
                itemsIndexed(Suraslist) { index, suraName ->
                    SuraNames(suraName) {
                        val intent = Intent(context, QuranContentActivity::class.java).apply {
                            putExtra(context.getString(R.string.sura_name), suraName)
                            putExtra(context.getString(R.string.sura_index), index+1)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun SuraNames(suraName: String, onNameClick: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            suraName,
            color = Color.Black,
            fontSize = 27.sp,
            style = TextStyle(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.clickable {
                onNameClick(suraName)
            }
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