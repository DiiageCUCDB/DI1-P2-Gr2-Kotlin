package com.diiage.edusec.data.remote

import com.diiage.edusec.data.dto.ApiResponse
import com.diiage.edusec.data.dto.ChallengeApiResponse
import com.diiage.edusec.data.dto.ChallengesResult
import com.diiage.edusec.data.dto.PostResponsesApiResponse
import com.diiage.edusec.data.dto.PostResponsesRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

/**
 * Remote data source for challenges operations.
 * Handles all API calls related to challenges.
 *
 * @property client The HTTP client used for making network requests
 */
internal class ChallengeAPI(private val client: HttpClient) {

    /**
     * Fetches all challenges from the remote API with pagination support.
     *
     * @param page The page number to fetch (default: 1)
     * @param limit The number of challenges per page (default: 10)
     * @return [ApiResponse] containing [ChallengesResult] with pagination info
     * @throws Exception if the network request fails or response parsing fails
     */
    suspend fun getAllChallenges(
        page: Int = 1,
        limit: Int = 10
    ): ApiResponse<ChallengesResult> = client
            .get("challenges") {
                url {
                    parameters.append("page", page.toString())
                    parameters.append("limit", limit.toString())
                }
            }
            .accept(HttpStatusCode.OK)
            .body()

    suspend fun getChallengeDetails(id: String): ChallengeApiResponse =
        client
            .get("challenges/$id")
            .accept(HttpStatusCode.OK)
            .body()

    internal class ResponsesAPI(private val client: HttpClient) {

        suspend fun postResponses(request: PostResponsesRequestDto): PostResponsesApiResponse =
            client
                .post("responses") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }
                .accept(HttpStatusCode.OK)
                .body()
    }
}