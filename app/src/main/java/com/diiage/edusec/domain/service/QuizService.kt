package com.diiage.edusec.domain.service


import com.diiage.edusec.domain.mock.mockQuestionsByChallenge
import com.diiage.edusec.domain.model.QuizQuestion

data class QuizUiState(
    val currentQuestion: QuizQuestion?,
    val currentIndex: Int,
    val totalQuestions: Int,
    val isFinished: Boolean,
    val score: Int
)

class QuizService {
    private var questions: List<QuizQuestion> = emptyList()
    private var currentIndex: Int = 0
    private var score: Int = 0

    fun startQuiz(challengeId: String) {
        questions = mockQuestionsByChallenge[challengeId] ?: emptyList()
        currentIndex = 0
        score = 0
    }

    fun getState(): QuizUiState =
        QuizUiState(
            currentQuestion = questions.getOrNull(currentIndex),
            currentIndex = currentIndex + 1,
            totalQuestions = questions.size,
            isFinished = currentIndex >= questions.size || questions.isEmpty(),
            score = score
        )

    fun answer(isYes: Boolean): QuizUiState {
        if (isYes) score++
        currentIndex++
        return getState()
    }
}