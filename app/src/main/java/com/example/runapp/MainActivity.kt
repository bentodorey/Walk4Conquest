package com.example.runapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runapp.screens.*
import com.example.runapp.ui.theme.RunappTheme
import kotlinx.coroutines.launch

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
                    // LOAD / SPLASH
                    composable("start") {
                        LoadScreen(
                            onJoinClick = { nav.navigate("join") },
                            onLoginClick = { nav.navigate("login") }
                        )
                    }

                    // SIGNUP
                    composable("join") {
                        SignupScreen(
                            onBack = { nav.popBackStack() },
                            onSuccess = { nav.navigate("dados") }
                        )
                    }

                    // DADOS DO UTILIZADOR
                    composable("dados") {
                        DadosScreen(
                            onBack = { nav.popBackStack() },
                            onFinish = { nav.navigate("home") }
                        )
                    }

                    // LOGIN
                    composable("login") {
                        LoginScreen(
                            onBack = { nav.popBackStack() },
                            onSuccess = { nav.navigate("home") }
                        )
                    }

                    // HOME + SIDE MENU
                    composable("home") {
                        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                        val scope = rememberCoroutineScope()

                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                SideMenu(
                                    currentItem = DrawerItem.HOME,
                                    onHomeClick = {
                                        scope.launch { drawerState.close() }
                                    },
                                    onLeaderboardClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("leaderboard")
                                    },
                                    onProfileClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("profile")
                                    },
                                    onHistoryClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("history")
                                    },
                                    onSettingsClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("settings")
                                    },
                                    onLogoutClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("start") {
                                            popUpTo("start") { inclusive = true }
                                        }
                                    }
                                )
                            }
                        ) {
                            HomeScreen(
                                onOpenMap = { nav.navigate("map") },
                                onLogout = {
                                    nav.navigate("start") {
                                        popUpTo("start") { inclusive = true }
                                    }
                                },
                                onOpenLeaderboard = { nav.navigate("leaderboard") },
                                onOpenMenu = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                    }

                    // MAPA
                    composable("map") {
                        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                        val scope = rememberCoroutineScope()

                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                SideMenu(
                                    currentItem = DrawerItem.HOME,
                                    onHomeClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("home") {
                                            popUpTo("home") { inclusive = true }
                                        }
                                    },
                                    onLeaderboardClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("leaderboard")
                                    },
                                    onProfileClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("profile")
                                    },
                                    onHistoryClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("history")
                                    },
                                    onSettingsClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("settings")
                                    },
                                    onLogoutClick = {
                                        scope.launch { drawerState.close() }
                                        nav.navigate("start") {
                                            popUpTo("start") { inclusive = true }
                                        }
                                    }
                                )
                            }
                        ) {
                            MapScreen(
                                onBack = { nav.popBackStack() },
                                onOpenDrawer = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                    }

                    // LEADERBOARD
                    composable("leaderboard") {
                        LeaderboardScreen(
                            onBack = { nav.popBackStack() }
                        )
                    }

                    // PROFILE
                    composable("profile") {
                        ProfileScreen(
                            onBack = { nav.popBackStack() }
                        )
                    }

                    // RUNNING (CORRIDA EM TEMPO REAL)
                    composable("running") {
                        RunningScreen(
                            onBack = { nav.popBackStack() }
                        )
                    }

                    // HISTORY (HISTÓRICO DE CORRIDAS)
                    composable("history") {
                        HistoryScreen(
                            onBack = { nav.popBackStack() }
                        )
                    }

                    // SETTINGS
                    composable("settings") {
                        SettingsScreen(
                            onBack = { nav.popBackStack() },
                            onEditProfile = { nav.navigate("edit_profile") },
                            onDeleteAccount = {
                                nav.navigate("start") {
                                    popUpTo("start") { inclusive = true }
                                }
                            }
                        )
                    }

                    // EDIT PROFILE
                    composable("edit_profile") {
                        EditProfileScreen(
                            onBack = { nav.popBackStack() },
                            onSaveSuccess = { nav.popBackStack() }
                        )
                    }

                }
            }
        }
    }
}