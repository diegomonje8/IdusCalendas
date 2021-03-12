package es.nauticapps.iduscalendas.data.idus.repository

import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.data.config.local.IdusLocal
import es.nauticapps.iduscalendas.data.config.network.IdusNetwork
import es.nauticapps.iduscalendas.data.config.network.NetworkManager
import es.nauticapps.iduscalendas.data.idus.model.RequestNewCalendar
import es.nauticapps.iduscalendas.data.idus.model.toDaoModel
import es.nauticapps.iduscalendas.data.idus.model.toDomainModel
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject

class IdusRepositoryImpl @Inject constructor(private val network: NetworkManager, private val remote: IdusNetwork, private val local: IdusLocal) : IdusRepository {

    override suspend fun getAllCalendars(): List<CalendarDomainModel> {

        return if (network.isNetworkAvailable()) {
            var items = remote.getAllCalendars().items
            items.forEach { item ->
                if (item.id.isNullOrEmpty()) { item.id = ""}
                if (item.summary.isNullOrEmpty()) { item.summary = ""}
                if (item.description.isNullOrEmpty()) { item.description = ""}
                if (item.timeZone.isNullOrEmpty()) { item.timeZone = ""}
                if (item.accessRole.isNullOrEmpty()) { item.accessRole = ""}
            }
            return items.map { it.toDomainModel() }
        } else {
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
    }

    override suspend fun insertCalendar(calendar: CalendarDomainModel) {
        local.insertCalendar(calendar.toDaoModel())
    }

    override suspend fun newCalendar(name: String) {
        remote.newCalendar(RequestNewCalendar(summary = name))
    }


}