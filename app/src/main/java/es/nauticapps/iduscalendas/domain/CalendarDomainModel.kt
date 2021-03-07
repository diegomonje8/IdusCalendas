package es.nauticapps.iduscalendas.domain

import java.io.Serializable

data class CalendarDomainModel(
    val id: String = "",
    val summary: String = "",
    val description: String = "",
    val timeZone : String = "",
    val accessRole: String = ""
):Serializable


