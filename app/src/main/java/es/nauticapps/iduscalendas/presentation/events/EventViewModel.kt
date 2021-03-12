package es.nauticapps.iduscalendas.presentation.events

import dagger.hilt.android.lifecycle.HiltViewModel
import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.domain.EventDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val repository: IdusRepository): BaseViewModel<EventListState>() {

    override val defaultState: EventListState = EventListState()

    override fun onStartFirstTime() {}

    fun getAllEvents() {
        updateToLoadingState()

        executeCoroutines( {
            //val listResult = repository.getAllCalendars()
            val listResult = listOf<EventDomainModel>()
            updateToNormalState(EventListState(listResult))
        } , { error ->
            updateToErrorState(EventListState(), error)
        } )
    }
}