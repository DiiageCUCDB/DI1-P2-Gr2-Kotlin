package com.diiage.edusec.di

import com.diiage.edusec.domain.usecase.LoginService
import org.koin.dsl.module

val appModule = module {
    // Single instance (singleton) of LoginService
    single { LoginService() }

    // Add other dependencies here as needed
    // single { YourRepository() }
    // factory { YourUseCase() } // new instance each time
}