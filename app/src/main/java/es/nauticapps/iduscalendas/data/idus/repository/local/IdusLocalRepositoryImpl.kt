package es.nauticapps.iduscalendas.data.idus.repository.local

import es.nauticapps.iduscalendas.data.config.local.IdusLocal
import es.nauticapps.iduscalendas.data.idus.model.toDaoModel
import es.nauticapps.iduscalendas.data.idus.model.toDomainModel
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import javax.inject.Inject

class IdusLocalRepositoryImpl @Inject constructor(private val local: IdusLocal) {

    suspend fun getAllCalendars(): List<CalendarDomainModel> {
        val items = local.getAllCalendars()

        items.forEach { item ->
            if (item.id.isNullOrEmpty()) { item.id = ""}
            if (item.summary.isNullOrEmpty()) { item.summary = ""}
            if (item.description.isNullOrEmpty()) { item.description = ""}
            if (item.timeZone.isNullOrEmpty()) { item.timeZone = ""}
            if (item.accessRole.isNullOrEmpty()) { item.accessRole = ""}
        }
        return items.map { it.toDomainModel() }
    }

    suspend fun insertCalendar(calendar: CalendarDomainModel) {
        local.insertCalendar(calendar.toDaoModel())
    }

    suspend fun updateCalendar(calendar: CalendarDomainModel) {
        local.updateCalendar(calendar.toDaoModel())
    }

    suspend fun deleteCalendar(calendar: CalendarDomainModel) {
        local.deleteCalendar(calendar.toDaoModel())
    }

}