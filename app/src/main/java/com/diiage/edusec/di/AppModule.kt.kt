package com.diiage.edusec.di

import com.diiage.edusec.data.remote.ChallengeAPI
import com.diiage.edusec.data.remote.LoginAPI
import com.diiage.edusec.data.remote.createHttpClient
import com.diiage.edusec.data.repository.*
import com.diiage.edusec.domain.repository.*
import io.ktor.client.HttpClient
import org.koin.dsl.module

private const val RMAPI_URL = "http://98.66.234.231:8000/api/"

val appModule = module {

    single<HttpClient> {
        createHttpClient(
            baseUrl = RMAPI_URL
        )
    }

    // Single instance (singleton) of LoginService
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single { LoginAPI(get()) }

    single<UserSessionManager> { UserSessionManagerImpl() }


    single<ChallengeRepository> { ChallengeRepositoryImpl(get() ) }
    single { ChallengeAPI(get()) }
    single { ChallengeAPI.ResponsesAPI(get()) }
    single<QuizRepository> { QuizRepositoryImpl(get(), get()) }


    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}