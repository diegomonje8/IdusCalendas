package es.nauticapps.iduscalendas.domain

import es.nauticapps.iduscalendas.data.Calendar


interface IdusRepository {

    suspend fun getAllCalendars() : List<CalendarDomainModel>
    suspend fun insertCalendar(calendar: CalendarDomainModel)
    suspend fun newCalendar(name: String)


}