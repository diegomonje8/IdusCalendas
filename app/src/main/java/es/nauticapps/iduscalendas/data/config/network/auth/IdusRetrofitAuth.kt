package es.nauticapps.iduscalendas.data.config.network.auth


import es.nauticapps.iduscalendas.BuildConfig
import es.nauticapps.iduscalendas.data.config.network.auth.model.RequestIdusAuthDataModel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class IdusRetrofitAuth @Inject constructor() {

    lateinit var service: IdusServiceAuth

    fun loadRetrofit() : Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://oauth2.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build()
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .connectTimeout(90L, TimeUnit.SECONDS)
                .readTimeout(90L, TimeUnit.SECONDS)
                .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)



        return builder.build()

    }

    suspend fun getAuthToken() : String {
        loadRetrofit()

        val client = RequestIdusAuthDataModel(
                "1079213088528-87bg8cqlu35o68c4i90tpasvu4n3orib.apps.googleusercontent.com",
                "refresh_token",
                "1//03aVH2FfLlKEqCgYIARAAGAMSNwF-L9IrTynqZsBGbSUfsZfCJ_WQ8_N3xvShMPh0IqypLk9FZsNN7jS9i5DphDy7mee-2NCxdDI"
                )

        val response = service.getAuthToken(client.client_id, client.grand_type, client.refresh_token)
        return response.access_token


    }

}