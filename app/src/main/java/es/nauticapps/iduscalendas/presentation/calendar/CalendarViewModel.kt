package es.nauticapps.iduscalendas.presentation.calendar

import dagger.hilt.android.lifecycle.HiltViewModel
import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.data.idus.repository.remote.IdusRemoteRepositoryImpl
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel@Inject constructor(private val repository: IdusRepository): BaseViewModel<CalendarListState>() {

    override val defaultState: CalendarListState = CalendarListState()

    override fun onStartFirstTime() {}

    fun getAllCalendars() {
        updateToLoadingState(CalendarListState())

        executeCoroutines( {
            val listResult = repository.getALLCalendars()
            updateToNormalState(CalendarListState(listResult))
        } , { error ->
            updateToErrorState(CalendarListState(listOf()), error)
        } )
    }
}