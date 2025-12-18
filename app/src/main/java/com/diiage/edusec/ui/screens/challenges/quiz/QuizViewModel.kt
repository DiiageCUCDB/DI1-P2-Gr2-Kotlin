package com.diiage.edusec.ui.screens.challenges.quiz

import android.app.Application
import com.diiage.edusec.domain.model.QuizQuestion
import com.diiage.edusec.domain.model.ChallengeDetails
import com.diiage.edusec.domain.repository.QuizRepository
import com.diiage.edusec.ui.core.ViewModel
import org.koin.core.component.inject

data class QuizState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val totalQuestions: Int = 0,
    val answers: List<Boolean> = emptyList(),
    val isFinished: Boolean = false
) {
    val currentQuestion: QuizQuestion?
        get() = questions.getOrNull(currentIndex)
}

class QuizViewModel(
    application: Application
) : ViewModel<QuizState>(
    initialState = QuizState(),
    application = application
) {

    private val quizRepository: QuizRepository by inject()

    fun startQuiz(challengeId: String) {
        updateState { copy(isLoading = true, error = null) }

        collectData(
            source = { quizRepository.getChallengeDetails(challengeId) } // Flow<ChallengeDetails>
        ) {
            onSuccess { details: ChallengeDetails ->
                val questions = details.questions

                updateState {
                    copy(
                        isLoading = false,
                        questions = questions,
                        currentIndex = 0,
                        totalQuestions = questions.size,
                        answers = emptyList(),
                        isFinished = questions.isEmpty()
                    )
                }
            }

            onFailure { error ->
                updateState {
                    copy(
                        isLoading = false,
                        error = error.message ?: "Erreur lors du chargement du quiz"
                    )
                }
            }
        }
    }

    fun answer(isYes: Boolean) {
        val current = state.value
        if (current.isFinished || current.currentQuestion == null) return

        val newAnswers = current.answers + isYes
        val nextIndex = current.currentIndex + 1
        val finished = nextIndex >= current.questions.size

        updateState {
            copy(
                answers = newAnswers,
                currentIndex = nextIndex,
                isFinished = finished
            )
        }
    }

    fun getFinalScore(): Int = state.value.answers.count { it }
}
