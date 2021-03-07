package es.nauticapps.iduscalendas.data.config.local

import es.nauticapps.iduscalendas.data.config.local.IdusDao
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityCalendarDataModel
import javax.inject.Inject

class IdusLocal @Inject constructor(private val dao: IdusDao) {

    suspend fun getAllCalendars(): List<IdusEntityCalendarDataModel> {
        return dao.getAllCalendars()
    }

    suspend fun insertCalendar(calendar: IdusEntityCalendarDataModel) {
        dao.insertCalendar(calendar)
    }

    suspend fun updateCalendar(calendar: IdusEntityCalendarDataModel) {
        dao.updateCalendar(calendar)
    }

    suspend fun deleteCalendar(calendar: IdusEntityCalendarDataModel) {
        dao.deleteCalendar(calendar)
    }

}