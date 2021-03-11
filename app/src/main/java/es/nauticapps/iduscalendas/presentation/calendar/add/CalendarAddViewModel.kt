package es.nauticapps.iduscalendas.presentation.calendar.add

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseViewModel
import es.nauticapps.iduscalendas.domain.IdusRepository
import es.nauticapps.iduscalendas.presentation.calendar.CalendarListState
import es.nauticapps.iduscalendas.presentation.calendar.add.CalendarAddFragment.Companion.CODE_ALL_RIGHT
import es.nauticapps.iduscalendas.presentation.calendar.add.CalendarAddFragment.Companion.FIELD_KEY_TITLE
import javax.inject.Inject

@HiltViewModel
class CalendarAddViewModel  @Inject constructor(private val repository: IdusRepository) : BaseViewModel<CalendarAddListState>() {

    override val defaultState: CalendarAddListState = CalendarAddListState()

    override fun onStartFirstTime() {

    }

    fun onActionSetTitle(text: String) {
        checkDataState { state ->
            if (text != state.name) {
                updateDataState(state.copy(name = text))
            }
        }
    }

    fun onActionAddCalendar() {
        updateToLoadingState()

        checkDataState { state ->
            if (state.name.isNotEmpty()) {
                executeCoroutines({
                    repository.newCalendar(state.name)
                    updateToLoadingState(BaseExtraData(CODE_ALL_RIGHT))
                }, { error ->
                    Log.e( "ERROR DIG", error.toString())
                })
            } else {
                updateToErrorState(CalendarListState(), FieldErrorException(FIELD_KEY_TITLE))
            }
        }




    }


}