package es.nauticapps.iduscalendas.data.config.local

import androidx.room.*
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityCalendarDataModel
import es.nauticapps.iduscalendas.data.idus.model.IdusEventsDataModel


@Dao
interface IdusDao {

    @Query("SELECT * FROM calendars")
    suspend fun getAllCalendars(): List<IdusEntityCalendarDataModel>

    @Insert
    suspend fun insertCalendar(calendar: IdusEntityCalendarDataModel)

    @Update
    suspend fun updateCalendar(calendar: IdusEntityCalendarDataModel)

    @Delete
    suspend fun deleteCalendar(calendar: IdusEntityCalendarDataModel)

    @Query("DELETE FROM calendars WHERE status = 0")
    suspend fun deleteAllCalendars()



    @Query("SELECT * FROM events WHERE calendarId = :calendarId" )
    suspend fun getAllEvents(calendarId: String) : List<IdusEventsDataModel>

    @Query("DELETE FROM events WHERE calendarId = :calendarId" )
    suspend fun deleteAllEvents(calendarId: String)

    @Insert
    suspend fun insertEvent(event: IdusEventsDataModel)


}