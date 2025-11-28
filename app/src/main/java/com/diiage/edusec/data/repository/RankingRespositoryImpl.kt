package com.diiage.edusec.data.repository

import android.util.Log
import com.diiage.edusec.data.remote.RankingAPI
import com.diiage.edusec.domain.model.Rank
import com.diiage.edusec.domain.repository.RankingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RankingRespositoryImpl(
    private val rankingAPI: RankingAPI
) : RankingRepository {
    private val userRanking = mutableListOf<Rank>()
    private val teamsRanking = mutableListOf<Rank>()

    override suspend fun getUsersRanking(): Flow<List<Rank>> = flow {
        if(userRanking.isEmpty()){
            val response = rankingAPI.getPlayersLeaderboard(limit = 20)
            if (!response.success){
                throw Exception("Failed to fetch ranking: ${response.message}")
            }
            val rankingResult = response.result
            val ranking = rankingResult.map {
                Rank(
                    rank = it.rank,
                    name = it.name,
                    score = it.score
                )
            }
            userRanking.addAll(ranking)
        }
        emit(userRanking.toList())
    }
    override suspend fun getTeamsRanking(): Flow<List<Rank>> = flow{
        if(teamsRanking.isEmpty()){
            val response = rankingAPI.getTeamsLeaderboard(limit = 20)
            if (!response.success){
                throw Exception("Failed to fetch ranking: ${response.message}")
            }
            val rankingResult = response.result
            val ranking = rankingResult.map {
                Rank(
                    rank = it.rank,
                    name = it.name,
                    score = it.score
                )
            }
            teamsRanking.addAll(ranking)
        }
        emit(teamsRanking.toList())
    }
}