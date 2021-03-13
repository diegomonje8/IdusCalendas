package es.nauticapps.iduscalendas.data.config.network.auth

import es.nauticapps.iduscalendas.data.config.network.auth.model.ResponseIdusAuthDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface IdusServiceAuth {

    //@FormUrlEncoded
    @POST("token")
    suspend fun getAuthToken(@Query("client_id") clientId : String, @Query("grant_type") grandType : String, @Query("refresh_token")  refreshToken : String) : ResponseIdusAuthDataModel

    //@FormUrlEncoded
    @POST("token")
    fun refreshToken(@Query("client_id") clientId : String, @Query("grant_type") grandType : String, @Query("refresh_token")  refreshToken : String) : Call<ResponseIdusAuthDataModel>

}