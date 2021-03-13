package es.nauticapps.iduscalendas.presentation.events.edit

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.EventDomainModel

data class EventEditListState (
    val start : String = "",
    val end : String = "",
    val summary : String = "",
    val description : String = "",
    val location: String = "",
    val calendarId : String = "",
    val event: EventDomainModel? = null
): BaseViewState()

