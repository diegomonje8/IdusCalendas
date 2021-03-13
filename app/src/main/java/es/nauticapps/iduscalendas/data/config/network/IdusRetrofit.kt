package es.nauticapps.iduscalendas.data.config.network

import es.nauticapps.iduscalendas.BuildConfig
import es.nauticapps.iduscalendas.data.config.network.auth.IdusRetrofitAuth
import es.nauticapps.iduscalendas.data.config.network.auth.IdusServiceAuth
import es.nauticapps.iduscalendas.data.config.network.auth.OAuthLocal
import es.nauticapps.iduscalendas.data.config.network.auth.RefreshTokenAuthenticate
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class IdusRetrofit @Inject constructor(private val authRefresh: RefreshTokenAuthenticate, private val authLocal: OAuthLocal, private val authRetrofit: Retrofit) {

    fun loadRetrofit() : Retrofit  {
         val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/calendar/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
        authRefresh.service = authRetrofit.create(IdusServiceAuth::class.java)
        return retrofit

    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)


        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${authLocal.getPersistedOAuthToken()}")
                .build()
            chain.proceed(request)
        }


        builder.authenticator(authRefresh)
                .connectTimeout(90L, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)



        return builder.build()

    }

}