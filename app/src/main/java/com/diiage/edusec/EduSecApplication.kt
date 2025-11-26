// EduSecApplication.kt
package com.diiage.edusec

import android.app.Application
import com.diiage.edusec.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EduSecApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin events (optional - use Level.NONE for production)
            androidLogger(level = Level.ERROR)

            // Inject Android context
            androidContext(this@EduSecApplication)

            // Load your modules
            modules(appModule)
        }
    }
}