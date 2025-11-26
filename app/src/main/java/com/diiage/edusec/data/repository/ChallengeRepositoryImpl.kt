package com.diiage.edusec.data.repository

import com.diiage.edusec.data.remote.ChallengeAPI
import com.diiage.edusec.domain.model.Challenge
import com.diiage.edusec.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of [ChallengeRepository] that handles data operations for challenges.
 * Combines local and remote data sources.
 *
 * @property challengeAPI The remote data source for challenges
 */
internal class ChallengeRepositoryImpl(
    private val challengeAPI: ChallengeAPI
) : ChallengeRepository {
    private val allChallenges = mutableListOf<Challenge>()

    /**
     * Fetches a list of challenges from the data source. The function streams the results
     * as a [Flow] of [List] of [Challenge] objects.
     * Initially loads from remote API and maintains local cache.
     *
     * @return A flow emitting a list of challenges.
     */
    override suspend fun getAllChallenges(): Flow<List<Challenge>> = flow {
        if (allChallenges.isEmpty()) {
            val response = challengeAPI.getAllChallenges(page = 1, limit = 20)

            if (!response.success) {
                throw Exception("Failed to fetch challenges: ${response.message}")
            }

            val challengesResult = response.result
            val challenges = challengesResult.challenges.map {
                Challenge(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    difficulty = it.difficulty,
                    isGuildChallenge = it.isGuildChallenge
                )
            }

            allChallenges.addAll(challenges)
        }
        emit(allChallenges.toList())
    }
}