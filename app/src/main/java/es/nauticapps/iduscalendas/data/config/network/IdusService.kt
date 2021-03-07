package es.nauticapps.iduscalendas.data.config.network

import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IdusService {

    @GET("users/me/calendarList")
    suspend fun getAllCalendars(): CalendarResponseDataModel


}