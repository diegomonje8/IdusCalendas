package es.nauticapps.iduscalendas.data.idus.repository.remote

import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.data.config.IdusNetwork

class IdusRemoteRepository {

    suspend fun getAllCalendars() : List<Calendar> {
        return IdusNetwork().getAllCalendars().items
    }

}