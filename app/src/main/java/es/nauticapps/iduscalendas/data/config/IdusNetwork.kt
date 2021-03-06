package es.nauticapps.iduscalendas.data.config

import es.nauticapps.iduscalendas.BuildConfig
import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class IdusNetwork {

    lateinit var service: IdusService

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/calendar/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
        service = retrofit.create(IdusService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        val authToken = "ya29.a0AfH6SMCkHajKA50hli9z5eSqCfszA-5kukuGWBQcRR1EgtV6TPUYQWXi-3o5p9TiMb9rElQmAkmnJ2sKfW-urTJqyJjezTA9pHxkCnGbY-vatvgTKsLjFHe1FJOJn8nKZw1KFMQHEoREiEXghNvLO2QhiHdB"

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(request)
        }

        return builder.build()

    }

    suspend fun getAllCalendars() : CalendarResponseDataModel {

        loadRetrofit()
        return service.getAllCalendars()
    }


}