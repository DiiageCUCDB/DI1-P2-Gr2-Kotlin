package com.diiage.edusec.di

import com.diiage.edusec.data.remote.ChallengeAPI
import com.diiage.edusec.data.remote.LoginAPI
import com.diiage.edusec.data.remote.RankingAPI
import com.diiage.edusec.data.remote.createHttpClient
import com.diiage.edusec.data.repository.*
import com.diiage.edusec.domain.repository.*
import io.ktor.client.HttpClient
import org.koin.dsl.module

private const val RMAPI_URL = "http://10.4.0.194:8000/api/"

val appModule = module {

    single<HttpClient> {
        createHttpClient(
            baseUrl = RMAPI_URL
        )
    }

    // Single instance (singleton) of LoginService
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single { LoginAPI(get()) }

    single<ChallengeRepository> { ChallengeRepositoryImpl(get() ) }
    single { ChallengeAPI(get()) }

    single<RankingRepository> { RankingRespositoryImpl(get()) }
    single { RankingAPI(get()) }



    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}