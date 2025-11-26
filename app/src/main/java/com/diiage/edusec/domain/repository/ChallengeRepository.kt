package com.diiage.edusec.domain.repository

import kotlinx.coroutines.flow.Flow
import com.diiage.edusec.domain.model.Challenge

interface ChallengeRepository {
    /**
     * Fetches a list of challenges from the data source. The function streams the results
     * as a [Flow] of [List] of [Challenge] objects.
     *
     * @return A flow emitting a list of challenges.
     */
    suspend fun getAllChallenges(): Flow<List<Challenge>>
}