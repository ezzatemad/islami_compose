package com.example.islamicompose.mainactivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.islamicompose.quran.QuranScreen
import com.example.islamicompose.R
import com.example.islamicompose.hadeth.HadethScreen
import com.example.islamicompose.radio.RadioScreen
import com.example.islamicompose.sebha.SebhaScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route ?: "home"


    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.default_bg),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = stringResource(R.string.default_background)
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent,
        ) {
            Scaffold(
                modifier = Modifier.background(Color.Transparent),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                stringResource(R.string.islami),
                                fontSize = 24.sp,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = Color.Black
                        )
                    )
                },
                containerColor = Color.Transparent,
                bottomBar = {
                    NavigationBar(
                        containerColor = colorResource(R.color.gold)
                    ) {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.quran_bottom),
                                    contentDescription = stringResource(R.string.quran_icon)
                                )
                            },
                            label = {
                                if (currentRoute == stringResource(R.string.quranscreen)) Text(
                                    stringResource(R.string.quran)
                                )
                            },
                            selected = currentRoute == stringResource(R.string.quranscreen),
                            onClick = {
                                if (currentRoute != "QuranScreen") {
                                    navController.navigate("QuranScreen") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.White,
                                indicatorColor = Color.Transparent
                            )
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.hadeth_bottom),
                                    contentDescription = stringResource(R.string.hadeth_icon)
                                )
                            },
                            label = {
                                if (currentRoute == stringResource(R.string.hadethscreen)) Text(
                                    stringResource(R.string.hadeth)
                                )
                            },
                            selected = currentRoute == "HadethScreen",
                            onClick = {
                                if (currentRoute != "HadethScreen") {
                                    navController.navigate("HadethScreen") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.White,
                                indicatorColor = Color.Transparent
                            )
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.sebha_bottom),
                                    contentDescription = stringResource(R.string.sebha_icon)
                                )
                            },
                            label = {
                                if (currentRoute == stringResource(R.string.sebhascreen)) Text(
                                    stringResource(R.string.sebha)
                                )
                            },
                            selected = currentRoute == "SebhaScreen",
                            onClick = {
                                if (currentRoute != "SebhaScreen") {
                                    navController.navigate("SebhaScreen") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.White,
                                indicatorColor = Color.Transparent
                            )
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.radio_bottom),
                                    contentDescription = stringResource(R.string.radio_icon)
                                )
                            },
                            label = {
                                if (currentRoute == stringResource(R.string.radioscreen)) Text(
                                    stringResource(R.string.radio)
                                )
                            },
                            selected = currentRoute == "RadioScreen",
                            onClick = {
                                if (currentRoute != "RadioScreen") {
                                    navController.navigate("RadioScreen") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.White,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    NavigationHost(navController)
                }
            }
        }
    }
}


@Composable
fun NavigationHost(navControl: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navControl, startDestination = "RadioScreen", modifier = modifier) {
        composable(route = "QuranScreen") { QuranScreen() }
        composable(route = "HadethScreen") { HadethScreen() }
        composable(route = "SebhaScreen") { SebhaScreen() }
        composable(route = "RadioScreen") { RadioScreen() }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}