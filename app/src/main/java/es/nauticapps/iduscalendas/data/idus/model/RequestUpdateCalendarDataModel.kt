package es.nauticapps.iduscalendas.data.idus.model

import es.nauticapps.iduscalendas.domain.CalendarDomainModel


data class RequestUpdateCalendarDataModel(
    val summary: String,
    val description: String
)

fun CalendarDomainModel.toRequestUpateCalendarModel(): RequestUpdateCalendarDataModel {
    return RequestUpdateCalendarDataModel(summary, description)
}

