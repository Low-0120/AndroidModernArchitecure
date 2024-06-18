package di

import com.example.core.network.firebase.TokenProvider
import com.google.samples.apps.modernarchitercture.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit.ApiService
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    private const val AUTH_HEADER_KEY = "Authorization"

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val token = tokenProvider.getToken()
            if (token != null) {
                val requestBuilder = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .method(originalRequest.method, originalRequest.body)
                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }
    }

//    private fun addAppVersionHeader(requestBuilder: Request.Builder): Request.Builder =
//        requestBuilder.addHeader("App-Version", BuildConfig.APP_VERSION)

    private fun addOSTypeHeader(requestBuilder: Request.Builder): Request.Builder = requestBuilder
        .addHeader("OS-Type", "android")

//    private fun addLaravelHeader(requestBuilder: Request.Builder): Request.Builder = requestBuilder
//        .addHeader("X-Requested-With", "XMLHttpRequest")

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.request().newBuilder().apply {
//                addAppVersionHeader(this)
                addOSTypeHeader(this)
//                addLaravelHeader(this)
            }.build().let(chain::proceed)
        }
    }

    @Provides
    @Singleton
    fun provideAuthenticator(): Authenticator {
        return Authenticator { route, response ->
            // Add authenticator logic here, such as refreshing tokens
            null
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
        authInterceptor: Interceptor,
        authenticator: Authenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .addInterceptor(loggingInterceptor)
            .cache(null)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
