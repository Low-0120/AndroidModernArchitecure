package com.example.modern_architecture_template.di

import android.util.Log
import com.example.core.network.firebase.DefaultTokenProvider
import com.example.core.network.firebase.TokenProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTokenProvider(): TokenProvider {
        Log.d("AppModule", "AuthInterceptor called")
        return DefaultTokenProvider()
    }


    @Provides
    @Singleton
    fun provideFirebaseAuth(tokenProvider: TokenProvider): FirebaseAuth {
        val auth = FirebaseAuth.getInstance()
        runBlocking {
            val tokenResult = checkAuthType(auth)
            tokenResult.onSuccess { token ->
                tokenProvider.setToken(token)
                Log.d("o", "Firebase TokenLogging Success")
            }.onFailure {
                val anonymousToken = signInAnonymouslyAndGetToken(auth)
                tokenProvider.setToken(anonymousToken)
                Log.d("AppModule", "Firebase AnonymousTokenLogging Success")
            }
        }
        return auth
    }

    private suspend fun checkAuthType(auth: FirebaseAuth): Result<String> {
        val user = auth.currentUser
        return if (user != null) {
            val token = user.getIdToken(true).await().token
            Log.d("AppModule", "Firebase TokenLogging Success")
            if (token != null) {
                Result.success(token)
            } else {
                Result.failure(ErrorType.Throwable(ErrorType.NotLogin))
            }
        } else {
            Result.failure(ErrorType.Throwable(ErrorType.NotLogin))
        }
    }

    private suspend fun signInAnonymouslyAndGetToken(auth: FirebaseAuth): String {
        val result = auth.signInAnonymously().await()
        return result.user?.getIdToken(true)?.await()?.token ?: ""
    }
}
