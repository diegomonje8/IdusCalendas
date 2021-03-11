package es.nauticapps.iduscalendas.presentation.calendar.add

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.domain.CalendarDomainModel

data class CalendarAddListState(
    val name : String = "",
    val calendar : CalendarDomainModel? = null
):BaseViewState()
