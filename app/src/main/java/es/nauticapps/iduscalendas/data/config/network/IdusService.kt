package es.nauticapps.iduscalendas.data.config.network

import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import es.nauticapps.iduscalendas.data.idus.model.RequestNewCalendar
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface IdusService {

    @GET("users/me/calendarList")
    suspend fun getAllCalendars(): CalendarResponseDataModel


    @POST("calendars")
    suspend fun newCalendar(@Body summary: RequestNewCalendar)


}