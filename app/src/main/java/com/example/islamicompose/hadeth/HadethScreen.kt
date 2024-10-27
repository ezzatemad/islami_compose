package com.example.islamicompose.hadeth

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicompose.quran.HorizontalLine
import com.example.islamicompose.R
import com.example.islamicompose.hadethcontent.HaadethContentActivity


@Composable
fun HadethScreen() {

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
                painter = painterResource(R.drawable.iv_hadeth_header),
                contentDescription = stringResource(R.string.hadeth_image_header),
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
                stringResource(R.string.alahadyth),
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
                items(50) { hadethIndex ->
                    val hadethName = "الحديث رقم ${hadethIndex + 1}"
                    HadethsNames(
                        hadethName,
                        hadethIndex
                    ) { selectedHadethName, selectedHadethIndex ->
                        val intent = Intent(context, HaadethContentActivity::class.java).apply {
                            putExtra(context.getString(R.string.hadeth_name), selectedHadethName)
                            putExtra(
                                context.getString(R.string.hadeth_index),
                                selectedHadethIndex + 1
                            )
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun HadethsNames(
    hadethNumber: String,
    hadethIndex: Int,
    onNameClick: (String, Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            hadethNumber,
            color = Color.Black,
            fontSize = 27.sp,
            style = TextStyle(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.clickable {
                onNameClick(hadethNumber, hadethIndex)
            }
        )

        HorizontalLine(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(top = 10.dp, bottom = 10.dp)
        )
    }
}
