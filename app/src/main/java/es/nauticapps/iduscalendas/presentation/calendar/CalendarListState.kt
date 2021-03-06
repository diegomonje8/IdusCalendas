package es.nauticapps.iduscalendas.presentation.calendar

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.data.Calendar

data class CalendarListState(
    var calendars: List<Calendar> = listOf()
): BaseViewState()


