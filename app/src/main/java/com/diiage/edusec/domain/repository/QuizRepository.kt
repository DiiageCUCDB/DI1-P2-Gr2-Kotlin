package com.diiage.edusec.domain.repository

import com.diiage.edusec.domain.model.ChallengeDetails
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getChallengeDetails(id: String): Flow<ChallengeDetails>
    suspend fun postQuizResponses(userId: String, answerIds: List<String>): Flow<Int>

}
