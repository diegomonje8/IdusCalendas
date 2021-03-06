package es.nauticapps.iduscalendas.data.config

import es.nauticapps.iduscalendas.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class IdusRetrofit @Inject constructor() {

    fun loadRetrofit() : Retrofit  {
         return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/calendar/v3/")
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

        val authToken = "ya29.a0AfH6SMCubR77r1TonLGcCCnAAeLhmVmHNO1Ypcw_3d95-vNDDgqAvQHsXd5xZdrPyPnvrmlUJX78vi9kVmhUqrSbxIiKctgaVqFtpvsONJZ1lvpfN3Idr9-9raaxVzi7AZB9ANyq8mTanKuNaSaVLckkHxp1"

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(request)
        }

        return builder.build()

    }

}