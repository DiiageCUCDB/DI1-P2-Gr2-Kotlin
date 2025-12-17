package com.diiage.edusec.data.repository

import com.diiage.edusec.data.dto.PostResponsesRequestDto
import com.diiage.edusec.data.remote.ChallengeAPI
import com.diiage.edusec.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.diiage.edusec.domain.model.ChallengeDetails as DomainChallengeDetails
import com.diiage.edusec.domain.model.QuizAnswer
import com.diiage.edusec.domain.model.QuizQuestion

internal class QuizRepositoryImpl(
    private val challengeAPI: ChallengeAPI,
    private val responsesAPI: ChallengeAPI.ResponsesAPI // ✅ injecte aussi l’API /responses
) : QuizRepository {

    private val cache = mutableMapOf<String, DomainChallengeDetails>()

    override suspend fun getChallengeDetails(id: String): Flow<DomainChallengeDetails> = flow {
        cache[id]?.let { emit(it); return@flow }

        val response = challengeAPI.getChallengeDetails(id)

        if (!response.success) {
            throw Exception("Failed to fetch challenge details: ${response.message}")
        }

        val dto = response.result
            ?: response.results.firstOrNull()
            ?: throw Exception("Challenge details missing in API response")

        val domain = DomainChallengeDetails(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            difficulty = dto.difficulty,
            isGuildChallenge = dto.isGuildChallenge,
            questions = dto.questions.map { q ->
                QuizQuestion(
                    id = q.id,
                    questionText = q.questionText,
                    answers = q.answers.map { a ->
                        QuizAnswer(
                            id = a.id,
                            answerText = a.answer
                        )
                    }
                )
            }
        )

        cache[id] = domain
        emit(domain)
    }

    override suspend fun postQuizResponses(
        userId: String,
        answerIds: List<String>
    ): Flow<Int> = flow {
        if (userId.isBlank()) throw IllegalArgumentException("userId is blank")
        if (answerIds.isEmpty()) throw IllegalArgumentException("answerIds is empty")

        val response = responsesAPI.postResponses(
            PostResponsesRequestDto(
                userId = userId,
                answerIds = answerIds
            )
        )

        if (!response.success) {
            throw Exception("Failed to post responses: ${response.message}")
        }

        val score = response.result?.score
            ?: response.result.score
            ?: throw Exception("Score missing in API response")

        emit(score)
    }
}
