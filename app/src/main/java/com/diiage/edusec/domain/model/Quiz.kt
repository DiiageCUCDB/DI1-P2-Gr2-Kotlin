package com.diiage.edusec.domain.model

data class QuizChallengeDetail(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean,
    val question: QuizQuestion? = null
)

data class QuizQuestion(
    val id: String,
    val challengeId: String,
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