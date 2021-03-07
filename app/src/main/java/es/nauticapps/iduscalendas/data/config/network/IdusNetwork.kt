package es.nauticapps.iduscalendas.data.config.network


import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import javax.inject.Inject

class IdusNetwork @Inject constructor(private val service: IdusService) {

    suspend fun getAllCalendars() : CalendarResponseDataModel {
        val serc = service.getAllCalendars()
        return serc
    }


}