package com.diiage.edusec.ui.screens.challenges.quiz.quizResult

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizResultState(
    val score: Int = 0,
    val total: Int = 0,
    val pointsEarned: Int = 0
)

class QuizResultViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuizResultState())
    val state: StateFlow<QuizResultState> = _state.asStateFlow()

    fun init(score: Int, total: Int) {
        _state.update {
            it.copy(
                score = score,
                total = total,
                pointsEarned = score * 10
            )
        }
    }
}
