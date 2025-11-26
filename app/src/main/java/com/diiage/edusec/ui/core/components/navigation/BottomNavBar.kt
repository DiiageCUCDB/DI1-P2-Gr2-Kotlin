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
import com.diiage.edusec.ui.core.Destination
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.core.theme.YellowDiiage

data class NavItem(
    val iconOutline: Int,
    val iconFilled: Int,
    val destination: Destination, // Keep Destination object
    val label: String,
    val isSpecial: Boolean = false
)

val bottomNavItems = listOf(
    NavItem(R.drawable.ic_house_outline, R.drawable.ic_house_filled, Destination.Home, "Accueil"),
    NavItem(R.drawable.ic_users_outline, R.drawable.ic_users_filled, Destination.Guild, "Equipe"),
    NavItem(R.drawable.ic_swords_outline, R.drawable.ic_swords_filled, Destination.Challenges, "Challenges", true),
    NavItem(R.drawable.ic_trophy_outline, R.drawable.ic_trophy_filled, Destination.Ranking, "Classement"),
    NavItem(R.drawable.ic_settings_outline, R.drawable.ic_settings_filled, Destination.Settings, "Paramètres")
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
            val selected = currentRoute == item.destination.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.destination.route)
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
                            item.isSpecial -> YellowDiiage
                            else -> MaterialTheme.colorScheme.onBackground
                        }
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
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
fun rememberMockNavController(initialRoute: Destination): NavController {
    return rememberNavController()
}
@Preview(showBackground = true, name = "Light - All States")
@Composable
fun BottomNavBarPreview_AllStates() {
    EduSecTheme {
        Surface {
            Column {
                Text("Home Selected:", style = MaterialTheme.typography.labelMedium)
                val navController1 = rememberMockNavController(Destination.Home)
                BottomNavBar(navController = navController1)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Challenges Selected:", style = MaterialTheme.typography.labelMedium)
                val navController2 = rememberMockNavController(Destination.Challenges)
                BottomNavBar(navController = navController2)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Settings Selected:", style = MaterialTheme.typography.labelMedium)
                val navController3 = rememberMockNavController(Destination.Settings)
                BottomNavBar(navController = navController3)
            }
        }
    }
}