package com.example.islamicompose.sebha

import android.media.MediaPlayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicompose.R


@Composable
fun SebhaScreen() {

    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? = null


    var counter = remember { mutableStateOf(0) }

    var tasbeeh = remember { mutableStateOf("سبحان الله") }

    var isButtonEnabled by remember { mutableStateOf(true) }

    var rotationDegree by remember { mutableStateOf(0f) }

    val animatedRotation by animateFloatAsState(
        targetValue = rotationDegree,
    )

    LaunchedEffect(Unit) {
        isButtonEnabled = false
        mediaPlayer = MediaPlayer.create(context, R.raw.subhan_allah)
        mediaPlayer?.start()

        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.release()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.iv_head_of_seb7a),
            contentDescription = null,
            modifier = Modifier.offset(y = (30).dp, x = (3).dp)
        )
        Image(
            painter = painterResource(R.drawable.iv_body_of_seb7a),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer(rotationZ = animatedRotation)
        )
        Text(
            "عدد التسبيحات",
            fontSize = 28.sp,
            color = colorResource(R.color.islami_word_Arabic),
            style = TextStyle(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(top = 12.dp)
        )

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = colorResource(R.color.content_background),
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        ) {
            Text(
                text = counter.value.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.islami_word_Arabic),
                modifier = Modifier.padding(16.dp)
            )
        }
        Button(
            onClick = {
                counter.value += 1
                if (counter.value == 33 && tasbeeh.value == "سبحان الله") {
                    isButtonEnabled = false
                    tasbeeh.value = "الحمدلله"
                    counter.value = 0
                    mediaPlayer = MediaPlayer.create(context, R.raw.alhamd_allah)
                    mediaPlayer?.start()

                    mediaPlayer?.setOnCompletionListener {
                        isButtonEnabled = true
                        mediaPlayer?.release()
                    }
                } else if (counter.value == 33 && tasbeeh.value == "الحمدلله") {
                    isButtonEnabled = false
                    tasbeeh.value = "الله اكبر"
                    counter.value = 0
                    mediaPlayer = MediaPlayer.create(context, R.raw.allah_akbar)
                    mediaPlayer?.start()

                    mediaPlayer?.setOnCompletionListener {
                        isButtonEnabled = true
                        mediaPlayer?.release()
                    }
                } else if (counter.value == 33 && tasbeeh.value == "الله اكبر") {
                    isButtonEnabled = false
                    tasbeeh.value = "سبحان الله"
                    counter.value = 0

                    mediaPlayer = MediaPlayer.create(context, R.raw.subhan_allah)
                    mediaPlayer?.start()

                    mediaPlayer?.setOnCompletionListener {
                        isButtonEnabled = true
                        mediaPlayer?.release()
                    }
                }
                rotationDegree += 4f
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = colorResource(R.color.gold)),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = tasbeeh.value,
                color = Color.White,
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CPreview() {
    SebhaScreen()
}