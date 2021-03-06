package es.nauticapps.iduscalendas.presentation.calendar

import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.data.idus.repository.remote.IdusRemoteRepository

class CalendarViewModel: BaseViewModel<CalendarListState>() {

    override val defaultState: CalendarListState = CalendarListState()

    override fun onStartFirstTime() {}

    fun getAllCalendars() {
        updateToLoadingState(CalendarListState())

        executeCoroutines( {
            val listResult = IdusRemoteRepository().getAllCalendars()
            updateToNormalState(CalendarListState(listResult))
        } , { error ->
            updateToErrorState(CalendarListState(listOf()), error)
        } )
    }
}