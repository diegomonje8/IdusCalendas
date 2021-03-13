package es.nauticapps.iduscalendas.presentation.events

import es.nauticapps.iduscalendas.base.BaseViewState
import es.nauticapps.iduscalendas.domain.EventDomainModel

data class EventListState(
    var events: List<EventDomainModel> = listOf(),
):BaseViewState()
