package es.nauticapps.iduscalendas.presentation.calendar

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.domain.CalendarDomainModel

data class CalendarListState(
    var calendars: List<CalendarDomainModel> = listOf()
): BaseViewState()


