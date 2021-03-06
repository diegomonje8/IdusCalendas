package es.nauticapps.iduscalendas.data.idus.repository

import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.data.config.NetworkManager
import es.nauticapps.iduscalendas.data.idus.repository.remote.IdusRemoteRepositoryImpl
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject

class IdusRepositoryImpl @Inject constructor(private val network: NetworkManager, private val remote: IdusRemoteRepositoryImpl ) : IdusRepository {


    override suspend fun getAllCalendars(): List<Calendar> {

        return if (network.isNetworkAvailable()) {
            remote.getAllCalendars()
        } else {
            listOf()
        }

    }


}