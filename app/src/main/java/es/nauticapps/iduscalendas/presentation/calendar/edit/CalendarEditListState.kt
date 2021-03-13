package es.nauticapps.iduscalendas.presentation.calendar.edit

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.domain.CalendarDomainModel

data class CalendarEditListState(
        val title : String = "",
        val description : String = "",
        val calendar : CalendarDomainModel? = null
): BaseViewState()