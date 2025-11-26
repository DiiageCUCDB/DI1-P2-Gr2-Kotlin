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
    suspend fun getChallenges(): Flow<List<Challenge>>

    /**
     * Loads more challenges from the data source, usually used for pagination purposes.
     * This function typically fetches the next set of challenges and appends them to the existing list.
     */
    suspend fun loadMore()

    /**
     * Starts a specific challenge, typically initializing the quiz or activity.
     *
     * @param challengeId The unique identifier of the challenge to start.
     */
    suspend fun startChallenge(challengeId: String)
}