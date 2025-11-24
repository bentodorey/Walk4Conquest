package com.example.runapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runapp.screens.*
import com.example.runapp.ui.theme.RunappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RunappTheme {
                val nav = rememberNavController()

                NavHost(
                    navController = nav,
                    startDestination = "start"
                ) {
                    composable("start") {
                        LoadScreen(
                            onJoinClick = { nav.navigate("join") },
                            onLoginClick = { nav.navigate("login") }
                        )
                    }

                    composable("join") {
                        SignupScreen(
                            onBack = { nav.popBackStack() },
                            onSuccess = { nav.navigate("dados") }
                        )
                    }

                    composable("dados") {
                        DadosScreen(
                            onBack = { nav.popBackStack() },
                            onFinish = { nav.navigate("home") }
                        )
                    }


                    composable("login") {
                        LoginScreen(
                            onBack = { nav.popBackStack() },
                            onSuccess = { nav.navigate("home") }
                        )
                    }

                    composable("home") {
                        HomeScreen(
                            onOpenMap = { nav.navigate("map") },
                            onLogout = {
                                nav.navigate("start") {
                                    popUpTo("start") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("map") {
                        MapScreen(
                            onBack = { nav.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
