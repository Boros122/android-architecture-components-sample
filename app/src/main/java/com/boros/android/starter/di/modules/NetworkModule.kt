package com.boros.android.starter.di.modules

import android.os.Handler
import android.os.Looper
import com.boros.android.starter.core.network.ApiDefinition
import com.boros.android.starter.core.network.util.NetworkUtil
import com.boros.android.starter.shared.event.InternalServerErrorEvent
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiDefinition(retrofit: Retrofit): ApiDefinition {
        return retrofit.create(ApiDefinition::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
            client: OkHttpClient,
            jsonConverter: Converter.Factory,
            networkUtil: NetworkUtil
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(networkUtil.baseUrl)
                .addConverterFactory(jsonConverter)
                .client(client)
                .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
            @Named("headersInterceptor") headersInterceptor: Interceptor,
            @Named("errorHandlerInterceptor") errorHandlerInterceptor: Interceptor,
            loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headersInterceptor)
                .addInterceptor(errorHandlerInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }

    @Singleton
    @Provides
    @Named("headersInterceptor")
    fun provideHeadersInterceptor(networkUtil: NetworkUtil): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.headers(networkUtil.headers)
            chain.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    @Named("errorHandlerInterceptor")
    fun createErrorHandlerInterceptor(): Interceptor {
        return Interceptor { chain ->
            //interceptor for error handling
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.code == 500) {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    EventBus.getDefault().post(
                            InternalServerErrorEvent(
                                    response.message
                            )
                    )
                }
                return@Interceptor response
            }
            response
        }
    }

}