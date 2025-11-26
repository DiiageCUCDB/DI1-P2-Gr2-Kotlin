package com.diiage.edusec.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.diiage.edusec.ui.screens.challenges.ChallengesScreen
import com.diiage.edusec.ui.screens.challenges.quiz.quizResult.QuizResultScreen
import com.diiage.edusec.ui.screens.challenges.quiz.QuizScreen
import com.diiage.edusec.ui.screens.guild.GuildScreen
import com.diiage.edusec.ui.screens.home.HomeScreen
import com.diiage.edusec.ui.screens.login.LoginScreen
import com.diiage.edusec.ui.screens.ranking.RankingScreen
import com.diiage.edusec.ui.screens.settings.SettingsScreen
import com.diiage.edusec.ui.screens.splash.SplashScreen

sealed class Destination(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {

    // Authentication
    object Splash : Destination(route = "splash")
    object Login : Destination(route = "login")

    // Main App
    object Home : Destination(route = "home")
    object Guild : Destination(route = "guild")
    object Challenges : Destination(route = "challenges")
    object Ranking : Destination(route = "ranking")
    object Settings : Destination(route = "settings")

    // Quiz Flow
    class Quiz(quizId: String = "{id}") :
        Destination(
            route = "quiz/$quizId",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        )

    class QuizResult(score: String = "{score}", total: String = "{total}") :
        Destination(
            route = "quiz_result/$score/$total",
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        )
}

// Extension for NavGraphBuilder
fun NavGraphBuilder.composable(
    destination: Destination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = deepLinks
) { backStackEntry ->
    content(backStackEntry)
}

// Extension for NavController
fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = navigate(
    route = destination.route,
    navOptions = navOptions,
    navigatorExtras = navigatorExtras
)

// Helper function for quiz result navigation with proper NavOptions
fun NavController.navigateToQuizResult(score: Int, total: Int) {
    // Create NavOptions with popUpTo behavior
    val navOptions = NavOptions.Builder()
        .setPopUpTo(Destination.Quiz().route, inclusive = true)
        .build()

    navigate(
        Destination.QuizResult(score.toString(), total.toString()),
        navOptions = navOptions
    )
}

@Composable
fun EdusecNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route,
        modifier = modifier
    ) {
        // Authentication Flow
        composable(Destination.Splash) { SplashScreen(navController) }
        composable(Destination.Login) { LoginScreen(navController) }

        // Main App Flow
        composable(Destination.Home) { HomeScreen(navController) }
        composable(Destination.Guild) { GuildScreen(navController) }
        composable(Destination.Challenges) { ChallengesScreen(navController) }
        composable(Destination.Ranking) { RankingScreen(navController) }
        composable(Destination.Settings) { SettingsScreen(navController) }

        // Quiz Flow
        composable(Destination.Quiz()) { backStackEntry ->
            val challengeId = backStackEntry.arguments?.getString("id") ?: return@composable

            QuizScreen(
                challengeId = challengeId,
                onQuizFinished = { score, total ->
                    navController.navigateToQuizResult(score, total)
                }
            )
        }

        composable(Destination.QuizResult()) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val total = backStackEntry.arguments?.getInt("total") ?: 0

            QuizResultScreen(
                navController = navController,
                score = score,
                total = total
            )
        }
    }
}