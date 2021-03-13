package es.nauticapps.iduscalendas.data.idus.repository

import android.util.Log
import es.nauticapps.iduscalendas.data.config.local.IdusLocal
import es.nauticapps.iduscalendas.data.config.network.IdusNetwork
import es.nauticapps.iduscalendas.data.config.network.NetworkManager
import es.nauticapps.iduscalendas.data.idus.model.*
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.EventDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject

class IdusRepositoryImpl @Inject constructor(private val network: NetworkManager, private val remote: IdusNetwork, private val local: IdusLocal) : IdusRepository {

    //Calendars

    override suspend fun getAllCalendars(): List<CalendarDomainModel> {
        if (network.isNetworkAvailable()) {
            val remoteCalendars = remote.getAllCalendars().items.map { it.toDomainModel() }
            local.refreshCalendars(remoteCalendars.map { it.toDaoModel() })
            return remoteCalendars
        } else {
            return local.getAllCalendars().map { it.toDomainModel() }
        }
    }

    override suspend fun insertCalendar(calendar: CalendarDomainModel) {
        local.insertCalendar(calendar.toDaoModel())
    }

    override suspend fun newCalendar(name: String) {
        remote.newCalendar(RequestNewCalendarDataModel(summary = name))
    }

    override suspend fun deleteCalendar(calendar: CalendarDomainModel) {
        return remote.deleteCalendar(calendarId = calendar.id)
    }

    override suspend fun updateCalendar(calendar: CalendarDomainModel) {
        remote.updateCalendar(calendar.id, calendar.toRequestUpateCalendarModel())
    }

    //Events

    override suspend fun getAllEvents(calendarId: String): List<EventDomainModel> {




           val remoteEvents = remote.getAllEvents(calendarId).items.map { it.toDomainModel() }

           return remoteEvents

    }

    override suspend fun insertEvent(calendarId: String, event: EventDomainModel) {
        remote.newEvent(calendarId, event.toRequestModel())
    }

    override suspend fun deleteEvent(calendarId: String, event: EventDomainModel) {
        remote.deleteEvent(calendarId, event.id)
    }

    override suspend fun updateEvent(calendarId: String, event: EventDomainModel) {
        remote.updateEvent(calendarId, event.id, event.toRequestModel())
    }

    //Events




}