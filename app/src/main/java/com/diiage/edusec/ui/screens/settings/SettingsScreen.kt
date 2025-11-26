package com.diiage.edusec.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.ui.core.components.Screen
import com.diiage.edusec.ui.core.components.CustomTitle
import com.diiage.edusec.ui.core.components.input.PrimarySecondaryToggle
import com.diiage.edusec.ui.core.components.input.ThemeSwitcher
import com.diiage.edusec.ui.core.components.layout.MainScaffold
import com.diiage.edusec.ui.core.theme.ThemeState

@Composable
fun SettingsScreen(navController: NavController) {
    Screen(
        viewModel = viewModel<SettingsViewModel>(),
        navController = navController
    ) { state, viewModel ->
        Content(
            navController = navController,
            state = state,
            handleAction = viewModel::handleAction
        )
    }
}

@Composable
private fun Content(
    navController: NavController,
    state: SettingsContracts.UiState,
    handleAction: (SettingsContracts.UiAction) -> Unit
) {
    MainScaffold(navController = navController) { _ ->
        Column {
            CustomTitle("Paramètres")

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                // Theme Setting - Remove currentTheme parameter
                SettingRow(
                    title = "Thème :",
                    content = {
                        ThemeSwitcher(
                            onThemeChanged = {
                                handleAction(SettingsContracts.UiAction.ThemeChanged(ThemeState.System))
                            }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Accessibility Setting
                SettingRow(
                    title = "Accessibilité :",
                    content = {
                        PrimarySecondaryToggle(
                            isPrimarySelected = state.isAccessibilityMode,
                            onToggle = { enabled ->
                                handleAction(SettingsContracts.UiAction.AccessibilityModeToggled(enabled))
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SettingRow(
    title: String,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}