package com.diiage.edusec.ui.screens.challenges.quiz

import android.app.Application
import com.diiage.edusec.domain.model.QuizQuestion
import com.diiage.edusec.domain.model.ChallengeDetails
import com.diiage.edusec.domain.repository.QuizRepository
import com.diiage.edusec.domain.repository.UserSessionManager
import com.diiage.edusec.ui.core.ViewModel
import org.koin.core.component.inject

data class QuizState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val totalQuestions: Int = 0,
    val answers: List<Boolean> = emptyList(),

    val selectedAnswerIds: List<String> = emptyList(),

    val isFinished: Boolean = false,

    // ✅ soumission API
    val isSubmitting: Boolean = false,
    val submitError: String? = null,
    val submittedScore: Int? = null
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
    private val userSessionManager: UserSessionManager by inject()

    fun startQuiz(challengeId: String) {
        updateState {
            copy(
                isLoading = true,
                error = null,
                answers = emptyList(),
                selectedAnswerIds = emptyList(),
                isFinished = false,
                isSubmitting = false,
                submitError = null,
                submittedScore = null
            )
        }

        collectData(
            source = { quizRepository.getChallengeDetails(challengeId) }
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
                        selectedAnswerIds = emptyList(),
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

    fun answer(answerId: String) {
        val current = state.value
        if (current.isFinished || current.currentQuestion == null) return

        val newSelectedIds = current.selectedAnswerIds + answerId
        val nextIndex = current.currentIndex + 1
        val finished = nextIndex >= current.questions.size

        updateState {
            copy(
                selectedAnswerIds = newSelectedIds,
                currentIndex = nextIndex,
                isFinished = finished
            )
        }
    }

    // ✅ POST /responses : userId vient du UserSessionManager
    fun submitQuiz() {
        val s = state.value
        if (s.isSubmitting || s.submittedScore != null) return

        val userId = userSessionManager.getUser()?.id
        if (userId.isNullOrBlank()) {
            updateState { copy(submitError = "Impossible d'envoyer les réponses : utilisateur non connecté") }
            return
        }

        updateState { copy(isSubmitting = true, submitError = null) }

        collectData(
            source = { quizRepository.postQuizResponses(userId, s.selectedAnswerIds) } // Flow<Int>
        ) {
            onSuccess { apiScore: Int ->
                updateState { copy(isSubmitting = false, submittedScore = apiScore) }
            }

            onFailure { error ->
                updateState {
                    copy(
                        isSubmitting = false,
                        submitError = error.message ?: "Erreur lors de l'envoi des réponses"
                    )
                }
            }
        }
    }
}
