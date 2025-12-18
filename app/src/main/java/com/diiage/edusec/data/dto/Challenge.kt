package com.diiage.edusec.data.dto

import android.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class ChallengesResult(
    val challenges: List<Challenge>,
    val totalChallenges: Int,
    val totalPages: Int,
    val currentPage: Int
)

/**
 * Détail d’un challenge pour GET /challenges/{id}
 */
@Serializable
data class ChallengeApiResponse(
    val result: ChallengeDetails? = null,
    val results: List<ChallengeDetails> = emptyList(),
    @SerialName("generationTime_ms")
    val generationTimeMs: Double? = null,

    val success: Boolean,
    val message: String? = null
)

@Serializable
data class ResultWrapper(
    val challenge: ChallengeDetails? = null
)

@Serializable
data class ResultItem(
    val challenge: ChallengeDetails
)

@Serializable
data class ChallengeDetails(
    val id: String,
    val name: String,
    val description: String,
    val difficulty: Int,
    val isGuildChallenge: Boolean,
    val questions: List<QuizQuestionDto> = emptyList()
)

@Serializable
data class QuizQuestionDto(
    val id: String,
    val challengeId: String,
    val questionText: String,
    val answers: List<QuizAnswerDto> = emptyList()
)

@Serializable
data class QuizAnswerDto(
    val id: String,
    val questionId: String,
    val answer: String
)

/**
 * poster nos reponse au quiz /responses
 */

@Serializable
data class PostResponsesRequestDto(
    val userId: String,
    @SerialName("answerId")
    val answerIds: List<String>
)

@Serializable
data class ScoreDto(
    val score: Int
)