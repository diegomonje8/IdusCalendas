package es.nauticapps.iduscalendas.presentation.events.edit

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.domain.EventDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import es.nauticapps.iduscalendas.presentation.calendar.add.FieldErrorException
import javax.inject.Inject



@HiltViewModel
class EventEditViewModel  @Inject constructor(private val repository: IdusRepository) : BaseViewModel<EventEditListState>() {

    override val defaultState: EventEditListState = EventEditListState()

    override fun onStartFirstTime() {

    }

    fun setCalendar(event: EventDomainModel?, calendarId: String) {
        event?.let { eventToEdit ->
            updateToNormalState(
                EventEditListState(
                    start = eventToEdit.startDate,
                    end = event.endDate,
                    summary = event.summary,
                    description = event.description,
                    location = event.location,
                    calendarId = calendarId,
                    event = event
                )
            )
        }
    }

    fun onActionSetStart(text: String) {
        checkDataState { state ->
            if (text != state.start) { updateDataState(state.copy(start = text)) }
        }
    }
    fun onActionSetStop(text: String) {
        checkDataState { state ->
            if (text != state.end) { updateDataState(state.copy(end = text)) }
        }
    }
    fun onActionSetSummary(text: String) {
        checkDataState { state ->
            if (text != state.summary) { updateDataState(state.copy(summary = text)) }
        }
    }
    fun onActionSetDescription(text: String) {
        checkDataState { state ->
            if (text != state.description) { updateDataState(state.copy(description = text)) }
        }
    }
    fun onActionSetLocation(text: String) {
        checkDataState { state ->
            if (text != state.location) { updateDataState(state.copy(location = text)) }
        }
    }


    fun onActionSaveEvent() {
        updateToLoadingState()

        checkDataState { state ->
            if (state.summary.isNotEmpty() && state.description.isNotEmpty()) {
                executeCoroutines({
                    repository.updateEvent(state.calendarId, EventDomainModel(state.event?.id ?: "", state.summary, state.description, state.location, state.start, state.end))
                }, { error ->
                    Log.e( "ERROR DIG", error.toString())
                })
            } else {
                when {
                    state.summary.isEmpty() -> updateToErrorState(
                        EventEditListState(), FieldErrorException(
                            EventEditFragment.FIELD_KEY_SUMMARY)
                    )
                    state.description.isEmpty() -> updateToErrorState(
                        EventEditListState(), FieldErrorException(
                            EventEditFragment.FIELD_KEY_DESC)
                    )
                }
            }
        }
    }

    fun onActionAddEvent() {
        updateToLoadingState()

        checkDataState { state ->
            if (state.summary.isNotEmpty() && state.description.isNotEmpty()) {
                executeCoroutines({
                    repository.insertEvent(state.calendarId, EventDomainModel(state.event?.id ?: "", state.summary, state.description, state.location, state.start, state.end))
                }, { error ->
                    Log.e( "ERROR DIG", error.toString())
                })
            } else {
                when {
                    state.summary.isEmpty() -> updateToErrorState(
                        EventEditListState(), FieldErrorException(
                            EventEditFragment.FIELD_KEY_SUMMARY)
                    )
                    state.description.isEmpty() -> updateToErrorState(
                        EventEditListState(), FieldErrorException(
                            EventEditFragment.FIELD_KEY_DESC)
                    )
                }
            }
        }
    }

    fun onActionDeleteCalendar() {
        updateToLoadingState()

        checkDataState { state ->
            executeCoroutines({
                repository.deleteEvent(state.calendarId, EventDomainModel(state.event?.id ?: "", state.summary, state.description, state.location, state.start, state.end))
                updateToLoadingState(BaseExtraData(EventEditFragment.CODE_DELETE_RIGHT))
            }, { error ->
                Log.e( "ERROR DIG", error.toString())
            })
        }
    }

}