package es.nauticapps.iduscalendas.domain

import es.nauticapps.iduscalendas.data.Calendar


interface IdusRepository {

    //CAlendars
    suspend fun getAllCalendars() : List<CalendarDomainModel>
    suspend fun insertCalendar(calendar: CalendarDomainModel)
    suspend fun newCalendar(name: String)
    suspend fun deleteCalendar(calendar: CalendarDomainModel)
    suspend fun updateCalendar(calendar: CalendarDomainModel)

    //EVENTS
    suspend fun getAllEvents(calendarId: String) : List<EventDomainModel>
    suspend fun insertEvent(calendarId: String, event: EventDomainModel)
    suspend fun deleteEvent(calendarId: String, event: EventDomainModel)
    suspend fun updateEvent(calendarId: String, event: EventDomainModel)


}