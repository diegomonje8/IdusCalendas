package es.nauticapps.iduscalendas.presentation.calendar.edit

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import es.nauticapps.iduscalendas.presentation.calendar.CalendarListState
import es.nauticapps.iduscalendas.presentation.calendar.add.CalendarAddFragment
import es.nauticapps.iduscalendas.presentation.calendar.add.CalendarAddListState
import es.nauticapps.iduscalendas.presentation.calendar.add.FieldErrorException
import javax.inject.Inject

@HiltViewModel
class CalendarEditViewModel  @Inject constructor(private val repository: IdusRepository) : BaseViewModel<CalendarEditListState>() {

    override val defaultState: CalendarEditListState = CalendarEditListState()

    override fun onStartFirstTime() {

    }

    fun setCalendar(calendar: CalendarDomainModel?) {
        calendar?.let { calendarToedit ->
            updateToNormalState(CalendarEditListState(
                title = calendarToedit.summary,
                description = calendarToedit.description,
                calendar = calendarToedit
            ))
        }
    }

    fun onActionSetTitle(text: String) {
        checkDataState { state ->
            if (text != state.title) {
                updateDataState(state.copy(title = text))
            }
        }
    }

    fun onActionSetDesc(text: String) {
        checkDataState { state ->
            if (text != state.description) {
                updateDataState(state.copy(description = text))
            }
        }
    }


    fun onActionSaveCalendar() {
        updateToLoadingState()

        checkDataState { state ->
            if (state.title.isNotEmpty() && state.description.isNotEmpty()) {
                executeCoroutines({
                    repository.updateCalendar(CalendarDomainModel(state.calendar?.id ?: "", summary = state.title, description = state.description, timeZone = state.calendar?.timeZone ?: "", accessRole = state.calendar?.accessRole ?: "" ))
                    updateToLoadingState(BaseExtraData(CalendarEditFragment.CODE_UPDATE_RIGHT))
                }, { error ->
                    Log.e( "ERROR DIG", error.toString())
                })
            } else {
                when {
                    state.title.isEmpty() -> updateToErrorState(CalendarEditListState(), FieldErrorException(CalendarEditFragment.FIELD_KEY_TITLE))
                    state.description.isEmpty() -> updateToErrorState(CalendarEditListState(), FieldErrorException(CalendarEditFragment.FIELD_KEY_DESC))
                }
            }
        }
    }

    fun onActionDeleteCalendar() {
        updateToLoadingState()

        checkDataState { state ->
            executeCoroutines({
                repository.deleteCalendar((CalendarDomainModel(state.calendar?.id ?: "", summary = state.title, description = state.description, timeZone = state.calendar?.timeZone ?: "", accessRole = state.calendar?.accessRole ?: "" )))
                updateToLoadingState(BaseExtraData(CalendarEditFragment.CODE_DELETE_RIGHT))
            }, { error ->
                Log.e( "ERROR DIG", error.toString())
            })
        }
    }

}


