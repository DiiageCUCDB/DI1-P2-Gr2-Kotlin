package com.diiage.edusec.ui.screens.challenges.quiz

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.edusec.ui.screens.challenges.quiz.quizQuestion.QuizQuestionScreen

@Composable
fun QuizScreen(
    challengeId: String,
    onQuizFinished: (score: Int, total: Int) -> Unit,
    viewModel: QuizViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(challengeId) {
        viewModel.startQuiz(challengeId)
    }

    LaunchedEffect(state.isFinished, state.totalQuestions) {
        if (state.isFinished && state.totalQuestions > 0) {
            val score = viewModel.getFinalScore()
            onQuizFinished(score, state.totalQuestions)
        }
    }

    when {
        state.isLoading -> {
            Text("Chargement du quiz…")
        }

        state.error != null -> {
            Text("Erreur : ${state.error}")
        }

        state.isFinished && state.totalQuestions == 0 -> {
            Text("Aucune question disponible pour ce quiz.")
        }

        else -> {
            QuizQuestionScreen(
                questionIndex = state.currentIndex + 1,
                totalQuestions = state.totalQuestions,
                questionText = state.currentQuestion?.questionText ?: "",
                onAnswerSelected = { yes -> viewModel.answer(yes) }
            )
        }
    }
}
