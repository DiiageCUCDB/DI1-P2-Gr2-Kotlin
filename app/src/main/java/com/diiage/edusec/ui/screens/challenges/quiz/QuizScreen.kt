// ✅ QuizScreen : plus besoin de userId, donc ta Navigation recompile
// et on déclenche submitQuiz() quand c’est fini, puis on navigue quand le score API arrive.

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

    // ✅ Quand fini : POST /responses
    LaunchedEffect(state.isFinished, state.totalQuestions) {
        if (state.isFinished && state.totalQuestions > 0) {
            viewModel.submitQuiz()
        }
    }

    // ✅ Quand score API reçu : on navigue vers l’écran résultat
    LaunchedEffect(state.submittedScore, state.totalQuestions) {
        val apiScore = state.submittedScore
        if (apiScore != null && state.totalQuestions > 0) {
            // petit garde-fou : si le backend renvoie un % (ex: 85), total = 100 sinon totalQuestions
            val totalForUi = if (apiScore > state.totalQuestions) 100 else state.totalQuestions
            onQuizFinished(apiScore, totalForUi)
        }
    }

    when {
        state.isLoading -> Text("Chargement du quiz…")
        state.error != null -> Text("Erreur : ${state.error}")
        state.submitError != null -> Text("Erreur : ${state.submitError}")
        state.isSubmitting -> Text("Calcul du score…")
        state.isFinished && state.totalQuestions == 0 -> Text("Aucune question disponible pour ce quiz.")
        else -> {
            QuizQuestionScreen(
                questionIndex = state.currentIndex + 1,
                totalQuestions = state.totalQuestions,
                questionText = state.currentQuestion?.questionText ?: "",
                answers = state.currentQuestion?.answers ?: emptyList(),
                onAnswerSelected = { answerId -> viewModel.answer(answerId) }
            )
        }
    }
}
