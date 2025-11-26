package com.diiage.edusec.ui.screens.challenges.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diiage.edusec.domain.mock.mockQuestionsByChallenge
import com.diiage.edusec.domain.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

class QuizViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state.asStateFlow()

    fun startQuiz(challengeId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val questions = mockQuestionsByChallenge[challengeId] ?: emptyList()

            _state.update {
                it.copy(
                    isLoading = false,
                    questions = questions,
                    currentIndex = 0,
                    totalQuestions = questions.size,
                    answers = emptyList(),
                    isFinished = questions.isEmpty()
                )
            }
        }
    }

    fun answer(isYes: Boolean) {
        val current = _state.value
        if (current.isFinished || current.currentQuestion == null) return

        val newAnswers = current.answers + isYes
        val nextIndex = current.currentIndex + 1
        val finished = nextIndex >= current.questions.size

        _state.update {
            it.copy(
                answers = newAnswers,
                currentIndex = nextIndex,
                isFinished = finished
            )
        }
    }

    fun getFinalScore(): Int = _state.value.answers.count { it }

}
