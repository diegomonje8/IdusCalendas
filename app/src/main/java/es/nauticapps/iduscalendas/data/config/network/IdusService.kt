package es.nauticapps.iduscalendas.data.config.network

import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import es.nauticapps.iduscalendas.data.idus.model.EventResponseDataModel
import es.nauticapps.iduscalendas.data.idus.model.RequestEventDataModel
import es.nauticapps.iduscalendas.data.idus.model.RequestNewCalendarDataModel
import es.nauticapps.iduscalendas.data.idus.model.RequestUpdateCalendarDataModel
import retrofit2.Response


import retrofit2.http.*


interface IdusService {

    //Calendars

    @GET("users/me/calendarList")
    suspend fun getAllCalendars(): CalendarResponseDataModel

    @POST("calendars")
    suspend fun newCalendar(@Body summary: RequestNewCalendarDataModel)

    @DELETE("calendars/{calendarId}")
    suspend fun deleteCalendar(@Path("calendarId") calendarId: String) : Response<Unit>

    @PUT("calendars/{calendarId}")
    suspend fun updateCalendar(@Path("calendarId") calendarId: String, @Body params: RequestUpdateCalendarDataModel)

    //Events

    @GET("calendars/{calendarId}/events")
    suspend fun getAllEvents(@Path("calendarId") calendarId: String): EventResponseDataModel

    @POST("calendars/{calendarId}/events")
    suspend fun addEvent(@Path("calendarId") calendarId: String, @Body params: RequestEventDataModel)

    @DELETE("calendars/{calendarId}/events/{eventId}")
    suspend fun deleteEvent(@Path("calendarId") calendarId: String, @Path("eventId") eventId: String): Response<Unit>

    @PUT("calendars/{calendarId}/events/{eventId}")
    suspend fun updateEvent(@Path("calendarId") calendarId: String, @Path("eventId") eventId: String, @Body params: RequestEventDataModel)




}