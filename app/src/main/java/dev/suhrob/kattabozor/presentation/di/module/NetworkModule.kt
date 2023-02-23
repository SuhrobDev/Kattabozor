package dev.suhrob.kattabozor.presentation.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.suhrob.kattabozor.R
import dev.suhrob.kattabozor.core.extensions.getString
import dev.suhrob.kattabozor.core.utils.SharedPreference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dev.suhrob.kattabozor.data.remote.MainService
import dev.suhrob.kattabozor.data.repository.MainRepositoryImpl
import dev.suhrob.kattabozor.domain.repository.MainRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideEncryptedSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreference =
        SharedPreference.getInstance(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        sharedPreference: SharedPreference
    ): OkHttpClient {
        val chuckInterceptor = ChuckerInterceptor.Builder(context)
            .maxContentLength(500_000L)
            .alwaysReadResponseBody(true)
            .build()
        val builder = OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
            .addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${sharedPreference.token}"
                    )
                    .addHeader("lang", sharedPreference.lang)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            })
            .build()
        return builder
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonGsonConverterFactory: GsonConverterFactory,
        builder: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getString(R.string.base_url))
            .client(builder)
            .addConverterFactory(gsonGsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideMainRepository(mainService: MainService): MainRepository {
        return MainRepositoryImpl(mainService)
    }

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}