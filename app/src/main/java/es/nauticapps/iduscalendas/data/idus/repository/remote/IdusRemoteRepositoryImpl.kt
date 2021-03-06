package es.nauticapps.iduscalendas.data.idus.repository.remote

import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.data.config.IdusNetwork
import javax.inject.Inject

class IdusRemoteRepositoryImpl @Inject constructor(private val network: IdusNetwork)  {

   suspend fun getAllCalendars(): List<Calendar> {
        return network.getAllCalendars().items
    }

}