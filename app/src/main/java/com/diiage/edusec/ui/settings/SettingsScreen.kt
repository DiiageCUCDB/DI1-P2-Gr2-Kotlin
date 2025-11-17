package com.diiage.edusec.ui.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diiage.edusec.ui.components.layout.CenteredBox
import com.diiage.edusec.ui.components.layout.CenteredColumn
import com.diiage.edusec.ui.components.layout.MainScaffold

@Composable
fun SettingsScreen(navController: NavController) {
    MainScaffold(navController = navController) { innerPadding ->
        CenteredBox(modifier = Modifier.padding(innerPadding)) {
            CenteredColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Écran Paramètres",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp
                )
            }
        }
    }
}