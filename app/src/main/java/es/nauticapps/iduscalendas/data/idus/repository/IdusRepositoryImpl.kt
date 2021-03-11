package es.nauticapps.iduscalendas.data.idus.repository

import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.data.config.network.NetworkManager
import es.nauticapps.iduscalendas.data.idus.repository.local.IdusLocalRepositoryImpl
import es.nauticapps.iduscalendas.data.idus.repository.remote.IdusRemoteRepositoryImpl
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject

class IdusRepositoryImpl @Inject constructor(private val network: NetworkManager, private val remote: IdusRemoteRepositoryImpl, private val local: IdusLocalRepositoryImpl ) : IdusRepository {


    override suspend fun getAllCalendars(): List<CalendarDomainModel> {

        return if (network.isNetworkAvailable()) {
            remote.getAllCalendars()
        } else {
            local.getAllCalendars()
        }
    }

    override suspend fun insertCalendar(calendar: CalendarDomainModel) {
        local.insertCalendar(calendar)
    }

    override suspend fun newCalendar(name: String) {
        remote.newCalendar(name)
    }


}