package es.nauticapps.iduscalendas.data.config.network


import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import es.nauticapps.iduscalendas.data.idus.model.*
import javax.inject.Inject


class IdusNetwork @Inject constructor(private val service: IdusService) {

    //Calendars

    suspend fun getAllCalendars() : CalendarResponseDataModel {
        val serc = service.getAllCalendars()
        return serc
    }

    suspend fun newCalendar(name: RequestNewCalendarDataModel) {
        service.newCalendar(name)
    }

    suspend fun deleteCalendar(calendarId: String) {
        service.deleteCalendar(calendarId =  calendarId)
    }

    suspend fun updateCalendar(calendarId: String, params: RequestUpdateCalendarDataModel) {
        service.updateCalendar(calendarId, params)
    }

    //Events

    suspend fun getAllEvents(calendarId: String) : EventResponseDataModel  {
        return service.getAllEvents(calendarId)
    }

    suspend fun newEvent(calendarId: String, params: RequestEventDataModel) {

        service.addEvent(calendarId = calendarId, params)
    }

    suspend fun deleteEvent(calendarId: String, eventId: String) {
        service.deleteEvent(calendarId = calendarId, eventId = eventId)
    }

    suspend fun updateEvent(calendarId: String, eventId: String, params: RequestEventDataModel) {
        service.updateEvent(calendarId = calendarId, eventId = eventId, params = params)
    }

}

data class FooRequest(
    val summary: String = "TestCalendar",
    val description: String = "Unadescription"
)