package com.diiage.edusec.data.remote

import com.diiage.edusec.data.dto.ApiResponse
import com.diiage.edusec.data.dto.Rank
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

internal class RankingAPI(private val client: HttpClient){
    suspend fun getPlayersLeaderboard(limit: Int = 20): ApiResponse<List<Rank>> = client
        .get("ranking/users") {
            url {
                parameters.append("limit", limit.toString())
            }
        }
        .accept(HttpStatusCode.OK)
        .body()

    suspend fun getTeamsLeaderboard(limit: Int = 20): ApiResponse<List<Rank>> = client
        .get("ranking/teams") {
            url {
                parameters.append("limit", limit.toString())
            }
        }
        .accept(HttpStatusCode.OK)
        .body()
}

