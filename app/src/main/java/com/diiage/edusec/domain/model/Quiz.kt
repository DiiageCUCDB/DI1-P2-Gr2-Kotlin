package com.diiage.edusec.domain.model

data class QuizQuestion(
    val id: String,
    val questionText: String,
    val answers: List<QuizAnswer>
)

data class QuizAnswer(
    val id: String,
    val answerText: String
)

data class QuizResponse(
    val userId: String,
    val answerId: String
)