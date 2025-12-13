package com.example.runapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onDeleteAccount: () -> Unit = {}
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Definições") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Seção: Conta
            SectionTitle("Conta")

            SettingsItem(
                icon = Icons.Default.Person,
                title = "Editar Perfil",
                subtitle = "Alterar nome, peso, altura",
                onClick = onEditProfile
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            SettingsItem(
                icon = Icons.Default.Lock,
                title = "Alterar Password",
                subtitle = "Mudar a sua palavra-passe",
                onClick = { /* TODO: Implementar */ }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Seção: Privacidade
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Privacidade")

            SettingsItem(
                icon = Icons.Default.Notifications,
                title = "Notificações",
                subtitle = "Gerir notificações da app",
                onClick = { /* TODO: Implementar */ }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            SettingsItem(
                icon = Icons.Default.LocationOn,
                title = "Localização",
                subtitle = "Permissões de GPS",
                onClick = { /* TODO: Abrir configurações do sistema */ }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Seção: Sobre
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Sobre")

            SettingsItem(
                icon = Icons.Default.Info,
                title = "Sobre a App",
                subtitle = "Versão 1.0.0",
                onClick = { /* TODO: Implementar */ }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            SettingsItem(
                icon = Icons.Default.Share,
                title = "Partilhar App",
                subtitle = "Convidar amigos",
                onClick = { /* TODO: Implementar */ }
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            // Seção: Zona Perigosa
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Zona Perigosa", color = MaterialTheme.colorScheme.error)

            SettingsItem(
                icon = Icons.Default.Delete,
                title = "Apagar Conta",
                subtitle = "Eliminar permanentemente a sua conta",
                onClick = { showDeleteDialog = true },
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    // Dialog de confirmação para apagar conta
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            icon = {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = { Text("Apagar Conta?") },
            text = {
                Text(
                    "Esta ação é irreversível! Todos os seus dados, corridas e conquistas serão permanentemente eliminados."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteAccount()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("APAGAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("CANCELAR")
                }
            }
        )
    }
}

@Composable
private fun SectionTitle(
    text: String,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text.uppercase(),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    tint: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = tint
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}