package com.example.runapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.runapp.R

enum class DrawerItem {
    HOME, LEADERBOARD, PROFILE, HISTORY, SETTINGS
}

@Composable
fun SideMenu(
    currentItem: DrawerItem,
    onHomeClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // ⭐ LOGO NO TOPO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logonoback),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(56.dp)
                        .padding(end = 12.dp)
                )
                Text(
                    text = "Walk 4 Conquest",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(Modifier.height(24.dp))

            // ITENS DO MENU
            NavigationDrawerItem(
                label = { Text("Home") },
                selected = currentItem == DrawerItem.HOME,
                onClick = onHomeClick,
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            NavigationDrawerItem(
                label = { Text("Leaderboard") },
                selected = currentItem == DrawerItem.LEADERBOARD,
                onClick = onLeaderboardClick,
                icon = { Icon(Icons.Filled.EmojiEvents, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            NavigationDrawerItem(
                label = { Text("Profile") },
                selected = currentItem == DrawerItem.PROFILE,
                onClick = onProfileClick,
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            NavigationDrawerItem(
                label = { Text("History") },
                selected = currentItem == DrawerItem.HISTORY,
                onClick = onHistoryClick,
                icon = { Icon(Icons.Filled.History, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            NavigationDrawerItem(
                label = { Text("Settings") },
                selected = currentItem == DrawerItem.SETTINGS,
                onClick = onSettingsClick,
                icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.weight(1f))

            NavigationDrawerItem(
                label = { Text("Logout") },
                selected = false,
                onClick = onLogoutClick,
                icon = { Icon(Icons.Filled.Logout, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
