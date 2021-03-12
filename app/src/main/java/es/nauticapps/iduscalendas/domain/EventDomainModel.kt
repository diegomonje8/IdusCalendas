package es.nauticapps.iduscalendas.domain

import java.io.Serializable

data class EventDomainModel(
    val id: String = "",
    val summary: String = "",
    val description: String = "",
    val location : String = "",
    val startDate: String = "",
    val endDate: String = ""
):Serializable