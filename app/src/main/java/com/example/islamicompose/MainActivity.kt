package com.example.islamicompose

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


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


    // Wrapping the entire content in a Box
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.default_bg),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
        // Use a Surface to define the elevation for the Scaffold
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent,
        ) {
            Scaffold(
                modifier = Modifier.background(Color.Transparent),
                topBar = {
                    // Center the TopAppBar Title
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                "Islami",
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
                bottomBar = {
                    NavigationBar(
                        containerColor = colorResource(R.color.gold)
                    ) {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.quran_bottom),
                                    contentDescription = "Quran"
                                )
                            },
                            label = { if (currentRoute == "QuranScreen") Text("Quran") },
                            selected = currentRoute == "QuranScreen",
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
                                    contentDescription = "Hadeth"
                                )
                            },
                            label = { if (currentRoute == "HadethScreen") Text("Hadeth") },
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
                                    contentDescription = "Sebha"
                                )
                            },
                            label = { if (currentRoute == "SebhaScreen") Text("Sebha") },
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
                                    contentDescription = "Radio"
                                )
                            },
                            label = { if (currentRoute == "RadioScreen") Text("Radio") },
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
                // Make sure the content area is transparent to allow the background to show through
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

    NavHost(navController = navControl, startDestination = "QuranScreen", modifier = modifier) {
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