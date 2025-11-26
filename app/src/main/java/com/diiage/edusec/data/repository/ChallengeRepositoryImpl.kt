package com.diiage.edusec.data.repository

import com.diiage.edusec.domain.mock.mockChallenges
import com.diiage.edusec.domain.model.Challenge
import com.diiage.edusec.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChallengeRepositoryImpl : ChallengeRepository {

    override suspend fun getChallenges(): Flow<List<Challenge>> = flow {
        emit(mockChallenges)
    }

    override suspend fun loadMore() {
        // Implementation for loading more challenges
        // Could fetch additional data from a remote source or database
        println("Loading more challenges...")
    }

    override suspend fun startChallenge(challengeId: String) {
        // Implementation for starting a challenge
        // Could initialize quiz state, track start time, etc.
        println("Starting challenge: $challengeId")
    }
}