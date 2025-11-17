package com.diiage.edusec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.ui.challenges.ChallengesScreen
import com.diiage.edusec.ui.edusec.EduSecScreen
import com.diiage.edusec.ui.guild.GuildScreen
import com.diiage.edusec.ui.home.HomeScreen
import com.diiage.edusec.ui.login.LoginScreen
import com.diiage.edusec.ui.settings.SettingsScreen
import com.diiage.edusec.ui.splash.SplashScreen
import com.diiage.edusec.ui.test.ComponentsTestScreen
import com.diiage.edusec.ui.theme.EduSecTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduSecTheme {
                EdusecApp()
            }
        }
    }
}

@Composable
fun EdusecApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        EdusecNavHost(navController)
    }
}

@Composable
fun EdusecNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("components_test") { ComponentsTestScreen(navController) }
        composable("guild") { GuildScreen(navController) }
        composable("edusec") { EduSecScreen(navController) }
        composable("challenges") { ChallengesScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}