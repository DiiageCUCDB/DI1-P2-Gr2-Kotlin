package com.diiage.edusec.ui.screens.challenges.quiz.quizQuestion

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizQuestionState(
    val questionIndex: Int = 0,
    val totalQuestions: Int = 0,
    val questionText: String = ""
)

class QuizQuestionViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuizQuestionState())
    val state: StateFlow<QuizQuestionState> = _state.asStateFlow()

    fun setQuestion(
        index: Int,
        total: Int,
        text: String
    ) {
        _state.update {
            it.copy(
                questionIndex = index -1,// possiblemenet a modifier une fois api lier
                totalQuestions = total,
                questionText = text
            )
        }
    }
}
