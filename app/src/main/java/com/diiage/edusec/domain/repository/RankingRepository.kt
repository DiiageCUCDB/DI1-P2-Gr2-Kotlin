package com.diiage.edusec.domain.repository

import com.diiage.edusec.domain.model.Rank
import kotlinx.coroutines.flow.Flow

interface RankingRepository {
    suspend fun getUsersRanking(): Flow<List<Rank>>
    suspend fun getTeamsRanking(): Flow<List<Rank>>
}