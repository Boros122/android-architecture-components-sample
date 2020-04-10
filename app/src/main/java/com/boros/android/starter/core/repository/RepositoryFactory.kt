package com.boros.android.starter.core.repository

import android.content.Context
import androidx.room.Room
import com.boros.android.starter.R
import com.boros.android.starter.core.database.AppDatabase
import com.boros.android.starter.core.model.ErrorMessage
import com.boros.android.starter.core.network.APIDefinition
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RepositoryFactory {

    private const val TIME_OUT = 60L

    private const val DB_NAME = "db_1"

    private const val baseUrlValue = "https://api.github.com/"

    private lateinit var apiDefinition: APIDefinition

    private lateinit var retrofit: Retrofit

    private lateinit var githubRepository: GithubRepository

    private lateinit var errorResponseConverter: Converter<ResponseBody, ErrorMessage>

    private lateinit var generalErrorMessage: String

    lateinit var db: AppDatabase

    fun init(context: Context) {
        generalErrorMessage = context.getString(R.string.general_error_message)
        initNetwork()
        db = initDB(context)
    }

    private fun initDB(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }

    private fun initNetwork() {
        val logging = createLoggingInterceptor()
        val interceptorForErrorHandling = createErrorHandlerInterceptor()

        val client = OkHttpClient.Builder()
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(interceptorForErrorHandling)
                .build()

        val gson = GsonBuilder().create()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(baseUrlValue)
                .build()
        apiDefinition = retrofit.create(APIDefinition::class.java)

        errorResponseConverter = retrofit.responseBodyConverter<ErrorMessage>(ErrorMessage::class.java, arrayOfNulls(0))

        githubRepository = GithubRepository(apiDefinition, generalErrorMessage)
    }

    @Throws(IOException::class)
    fun errorResponseFromResponseBody(response: ResponseBody): ErrorMessage? {
        return try {
            errorResponseConverter.convert(response) as ErrorMessage
        } catch (e: IOException) {
            null
        }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun createErrorHandlerInterceptor(): Interceptor {
        return Interceptor { chain ->
            //interceptor for error handling
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code == 500) {
                response
            } else response
        }
    }

    fun getGithubRepository() = githubRepository

}