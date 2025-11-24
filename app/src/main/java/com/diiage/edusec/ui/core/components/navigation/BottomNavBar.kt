package com.diiage.edusec.ui.core.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.R
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.core.theme.YellowDiiage

data class NavItem(
    val iconOutline: Int,
    val iconFilled: Int,
    val route: String,
    val label: String,
    val isSpecial: Boolean = false // Nouveau paramètre pour identifier l'icône spéciale (YELLOW)
)

val bottomNavItems = listOf(
    NavItem(R.drawable.ic_house_outline, R.drawable.ic_house_filled, "home", "Accueil"),
    NavItem(R.drawable.ic_users_outline, R.drawable.ic_users_filled, "guild", "Guild"),
    NavItem(R.drawable.ic_swords_outline, R.drawable.ic_swords_filled, "challenges", "challenges", true), // Icône spéciale
    NavItem(R.drawable.ic_trophy_outline, R.drawable.ic_trophy_filled, "ranking", "Classement"),
    NavItem(R.drawable.ic_settings_outline, R.drawable.ic_settings_filled, "settings", "Paramètres")
)

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (selected) item.iconFilled else item.iconOutline
                        ),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = when {
                            // Seule l'icône swords (spéciale) devient jaune quand sélectionnée
                            item.isSpecial -> YellowDiiage
                            else -> MaterialTheme.colorScheme.onBackground
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

// Mock NavController for previews
@Composable
fun rememberMockNavController(initialRoute: String = "home"): NavController {
    return rememberNavController()
}
@Preview(showBackground = true, name = "Light - All States")
@Composable
fun BottomNavBarPreview_AllStates() {
    EduSecTheme {
        Surface {
            Column {
                Text("Home Selected:", style = MaterialTheme.typography.labelMedium)
                val navController1 = rememberMockNavController("home")
                BottomNavBar(navController = navController1)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Challenges Selected:", style = MaterialTheme.typography.labelMedium)
                val navController2 = rememberMockNavController("challenges")
                BottomNavBar(navController = navController2)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Settings Selected:", style = MaterialTheme.typography.labelMedium)
                val navController3 = rememberMockNavController("settings")
                BottomNavBar(navController = navController3)
            }
        }
    }
}