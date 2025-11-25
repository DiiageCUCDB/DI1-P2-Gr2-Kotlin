package com.diiage.edusec.di

import com.diiage.edusec.data.repository.*
import com.diiage.edusec.domain.repository.*
import org.koin.dsl.module

val appModule = module {
    // Single instance (singleton) of LoginService
    single<LoginRepository> { LoginRepositoryImpl() }
    single<ChallengeRepository> { ChallengeRepositoryImpl() }

    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}