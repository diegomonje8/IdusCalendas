package es.nauticapps.iduscalendas.data.idus.repository.remote


import es.nauticapps.iduscalendas.data.config.network.IdusNetwork
import es.nauticapps.iduscalendas.data.idus.model.RequestNewCalendar
import es.nauticapps.iduscalendas.data.idus.model.toDomainModel
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import javax.inject.Inject

class IdusRemoteRepositoryImpl @Inject constructor(private val network: IdusNetwork)  {

   suspend fun getAllCalendars(): List<CalendarDomainModel> {
       var items = network.getAllCalendars().items
       items.forEach { item ->
           if (item.id.isNullOrEmpty()) { item.id = ""}
           if (item.summary.isNullOrEmpty()) { item.summary = ""}
           if (item.description.isNullOrEmpty()) { item.description = ""}
           if (item.timeZone.isNullOrEmpty()) { item.timeZone = ""}
           if (item.accessRole.isNullOrEmpty()) { item.accessRole = ""}
       }
       return items.map { it.toDomainModel() }

    }

    suspend fun newCalendar(name: String) {
        network.newCalendar(RequestNewCalendar(summary = name))
    }


}