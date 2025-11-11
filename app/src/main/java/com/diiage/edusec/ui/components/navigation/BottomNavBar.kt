package com.diiage.edusec.ui.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.R
import com.diiage.edusec.ui.theme.BlueDiiage
import com.diiage.edusec.ui.theme.EduSecTheme
import com.diiage.edusec.ui.theme.YellowDiiage

data class NavItem(
    val icon: Int, // Resource ID for the icon
    val route: String,
    val label: String // Keep label for accessibility
)

val bottomNavItems = listOf(
    NavItem(R.drawable.house, "home", "Accueil"),
    NavItem(R.drawable.users, "guild", "Guild"),
    NavItem(R.drawable.swords, "edusec", "EduSec"),
    NavItem(R.drawable.trophy, "challenges", "Challenges"),
    NavItem(R.drawable.settings, "settings", "Paramètres")
)

@Composable
fun BottomNavBar(
    navController: androidx.navigation.NavController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.height(70.dp)
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
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = if (selected) {
                            YellowDiiage
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = YellowDiiage,
                    selectedTextColor = YellowDiiage,
                    indicatorColor = BlueDiiage,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

// Mock NavController for previews
@Composable
fun rememberMockNavController(initialRoute: String = "home"): androidx.navigation.NavController {
    val context = LocalContext.current
    return rememberNavController().apply {
        // Set initial route for preview
        if (currentBackStackEntry == null) {
            // This is a simplified mock - in real usage, you'd need proper navigation setup
        }
    }
}

@Preview(showBackground = true, name = "Light - Home Selected")
@Composable
fun BottomNavBarPreview_HomeSelected() {
    val mockNavController = rememberMockNavController("home")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - EduSec Selected")
@Composable
fun BottomNavBarPreview_EduSecSelected() {
    val mockNavController = rememberMockNavController("edusec")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Challenges Selected")
@Composable
fun BottomNavBarPreview_ChallengesSelected() {
    val mockNavController = rememberMockNavController("challenges")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Score Selected")
@Composable
fun BottomNavBarPreview_ScoreSelected() {
    val mockNavController = rememberMockNavController("score")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Settings Selected")
@Composable
fun BottomNavBarPreview_SettingsSelected() {
    val mockNavController = rememberMockNavController("settings")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - All States")
@Composable
fun BottomNavBarPreview_AllStates() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
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

@Preview(showBackground = true, name = "Dark - Home Selected", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavBarDarkPreview_HomeSelected() {
    val mockNavController = rememberMockNavController("home")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Challenges Selected", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavBarDarkPreview_ChallengesSelected() {
    val mockNavController = rememberMockNavController("challenges")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Settings Selected", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavBarDarkPreview_SettingsSelected() {
    val mockNavController = rememberMockNavController("settings")

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                BottomNavBar(navController = mockNavController)
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - All States", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavBarDarkPreview_AllStates() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
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