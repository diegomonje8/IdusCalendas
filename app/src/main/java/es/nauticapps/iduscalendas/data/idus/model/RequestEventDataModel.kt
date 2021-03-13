package es.nauticapps.iduscalendas.data.idus.model

import es.nauticapps.iduscalendas.domain.EventDomainModel

data class RequestEventDataModel (

    val id: String = "",
    val summary: String = "",
    val description: String = "",
    val location : String = "",
    val start: RequestEventDate,
    val endDate: RequestEventDate,

)

data class RequestEventDate (
    val dateTime: String = ""
)

fun EventDomainModel.toRequestModel() : RequestEventDataModel {
    return RequestEventDataModel(id,summary,description,location,RequestEventDate(startDate),RequestEventDate(endDate))
}


