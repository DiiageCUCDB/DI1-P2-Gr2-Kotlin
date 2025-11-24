package com.diiage.edusec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.domain.service.*
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.screens.challenges.ChallengesScreen
import com.diiage.edusec.ui.screens.challenges.quiz.QuizResultScreen
import com.diiage.edusec.ui.screens.challenges.quiz.QuizScreen
import com.diiage.edusec.ui.screens.guild.GuildScreen
import com.diiage.edusec.ui.screens.home.HomeScreen
import com.diiage.edusec.ui.screens.login.LoginScreen
import com.diiage.edusec.ui.screens.ranking.RankingScreen
import com.diiage.edusec.ui.screens.settings.SettingsScreen
import com.diiage.edusec.ui.screens.splash.SplashScreen

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
    val quizService = remember { QuizService() }

    var uiState by remember {
        mutableStateOf(
            QuizUiState(
                currentQuestion = null,
                currentIndex = 0,
                totalQuestions = 0,
                isFinished = false,
                score = 0
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }

        composable("quiz/{id}") { backStack ->
            val challengeId = backStack.arguments?.getString("id") ?: return@composable

            LaunchedEffect(challengeId) {
                quizService.startQuiz(challengeId)
                uiState = quizService.getState()
            }

            QuizScreen(
                questionIndex = uiState.currentIndex,
                totalQuestions = uiState.totalQuestions,
                questionText = uiState.currentQuestion?.questionText ?: "",
                onAnswerSelected = { isYes ->
                    val newState = quizService.answer(isYes)
                    uiState = newState

                    if (newState.isFinished && newState.totalQuestions > 0) {
                        navController.navigate(
                            "quiz_result/${newState.score}/${newState.totalQuestions}"
                        ) {
                            popUpTo("quiz/{id}") { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(
            route = "quiz_result/{score}/{total}"
        ) { backStack ->
            val score = backStack.arguments?.getString("score")?.toIntOrNull() ?: 0
            val total = backStack.arguments?.getString("total")?.toIntOrNull() ?: 0

            QuizResultScreen(
                navController = navController,
                score = score,
                total = total,
                pointsEarned = score * 10
            )
        }

        composable("guild") { GuildScreen(navController) }
        composable("challenges") { ChallengesScreen(navController) }
        composable("ranking") { RankingScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}